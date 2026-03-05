package com.rocket.movielab.data.local.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rocket.movielab.data.local.database.PopularMovieDatabase
import com.rocket.movielab.data.local.entity.PopularMetadata
import com.rocket.movielab.data.local.entity.PopularMovieEntity
import com.rocket.movielab.data.local.entity.RemoteKeys
import com.rocket.movielab.data.mapper.toEntity
import com.rocket.movielab.data.remote.datasource.PopularMoviesRemoteDataSource

@OptIn(ExperimentalPagingApi::class)
class PopularMovieRemoteMediator(
    private val database: PopularMovieDatabase,
    private val api: PopularMoviesRemoteDataSource,
) : RemoteMediator<Int, PopularMovieEntity>() {
    private val popularMovieDao = database.popularMoviesDao()
    private val remoteKeysDao = database.remoteKeysDao()
    private val metadataDao = database.popularMetadataDao()

    private val staleTime = 60 * 60 * 1000L * 12 //? 12 Horas

    override suspend fun initialize(): InitializeAction {
        val metadata = metadataDao.getPopularMetadata()
        val currentTime = System.currentTimeMillis()

        return if (metadata == null || currentTime - metadata.lastUpdated > staleTime) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularMovieEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteForFirtsItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val response = api.getPopularMovies(page)

            if (response.isFailure) {
                return MediatorResult.Error(
                    response.exceptionOrNull() ?: Exception("Unknown error")
                )
            }

            val apiResult =
                response.getOrNull() ?: return MediatorResult.Error(Exception("Empty response"))
            val movies = apiResult.results
            val endOfPaginationReached = page >= apiResult.totalPages

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    popularMovieDao.clearAll()
                    remoteKeysDao.clearRemoteKeys()
                }

                val entities = movies.map {
                    it.toEntity(
                        page = apiResult.page,
                        cachedAt = System.currentTimeMillis()
                    )
                }

                val keys = entities.map {
                    RemoteKeys(
                        movieId = it.id,
                        nextKey = if (endOfPaginationReached) null else page + 1,
                        prevKey = if (page == 1) null else page - 1
                    )
                }

                popularMovieDao.insertAll(entities)
                remoteKeysDao.insertAll(keys)

                metadataDao.insertPopularMetadata(
                    PopularMetadata(
                        lastUpdated = System.currentTimeMillis()
                    )
                )
            }
            return MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PopularMovieEntity>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.remoteKeysMovieId(id)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PopularMovieEntity>
    ): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { remoteKeysDao.remoteKeysMovieId(it.id) }
    }

    private suspend fun getRemoteForFirtsItem(
        state: PagingState<Int, PopularMovieEntity>
    ): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { remoteKeysDao.remoteKeysMovieId(it.id) }
    }
}
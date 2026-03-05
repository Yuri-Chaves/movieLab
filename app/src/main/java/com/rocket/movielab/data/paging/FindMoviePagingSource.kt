package com.rocket.movielab.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rocket.movielab.data.mapper.toCard
import com.rocket.movielab.data.remote.datasource.FindMovieRemoteDataSource
import com.rocket.movielab.domain.model.MovieCard

class FindMoviePagingSource(
    private val remote: FindMovieRemoteDataSource,
    private val query: String
) : PagingSource<Int, MovieCard>() {
    private val numOfOffScreenPage: Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieCard> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remote.searchMovie(query, pageNumber)

            if (response.isSuccess) {
                val movie = response.getOrNull()
                val totalPages = movie?.totalPages ?: 0
                LoadResult.Page(
                    data = movie?.results?.map { it.toCard() } ?: emptyList(),
                    prevKey = if (pageNumber > 1) pageNumber - 1 else null,
                    nextKey = if (pageNumber < totalPages) pageNumber + 1 else null
                )
            } else {
                LoadResult.Error(response.exceptionOrNull() ?: Exception("Unknown error"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieCard>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)
                ?.prevKey?.plus(numOfOffScreenPage)
                ?: state.closestPageToPosition(anchor)
                    ?.nextKey?.minus(numOfOffScreenPage)
        }
    }
}
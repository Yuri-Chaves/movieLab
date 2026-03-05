package com.rocket.movielab.di

import androidx.room.Room
import com.rocket.movielab.data.local.dao.FavoriteMovieDao
import com.rocket.movielab.data.local.database.FAVORITE_MOVIES_DATABASE
import com.rocket.movielab.data.local.database.FavoriteMovieDatabase
import com.rocket.movielab.data.local.database.POPULAR_MOVIES_DATABASE
import com.rocket.movielab.data.local.database.PopularMovieDatabase
import com.rocket.movielab.data.remote.datasource.FindMovieRemoteDataSource
import com.rocket.movielab.data.remote.datasource.FindMovieRemoteDataSourceImp
import com.rocket.movielab.data.remote.datasource.MovieDetailsRemoteDataSource
import com.rocket.movielab.data.remote.datasource.MovieDetailsRemoteDataSourceImp
import com.rocket.movielab.data.remote.datasource.PopularMoviesRemoteDataSource
import com.rocket.movielab.data.remote.datasource.PopularMoviesRemoteDataSourceImp
import com.rocket.movielab.data.repository.FavoriteMoviesRepositoryImp
import com.rocket.movielab.data.repository.FindMovieRepositoryImp
import com.rocket.movielab.data.repository.MovieDetailsRepositoryImp
import com.rocket.movielab.data.repository.PopularMovieRepositoryImp
import com.rocket.movielab.domain.repository.FavoriteMoviesRepository
import com.rocket.movielab.domain.repository.FindMovieRepository
import com.rocket.movielab.domain.repository.MovieDetailsRepository
import com.rocket.movielab.domain.repository.PopularMovieRepository
import com.rocket.movielab.domain.usecase.FindMovieUseCase
import com.rocket.movielab.domain.usecase.MovieDetailsUseCase
import com.rocket.movielab.ui.screen.details.DetailsViewModel
import com.rocket.movielab.ui.screen.favorite.FavoriteViewModel
import com.rocket.movielab.ui.screen.find.FindViewModel
import com.rocket.movielab.ui.screen.trending.TrendingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object AppModules {
    val uiModule = module {
        viewModel { TrendingViewModel(get()) }
        viewModel { FindViewModel(get()) }
        viewModel { (movieId: Int) ->
            DetailsViewModel(movieId, get(), get())
        }

        viewModel { FavoriteViewModel(get()) }
    }

    val dataModule = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                PopularMovieDatabase::class.java,
                POPULAR_MOVIES_DATABASE
            )
                .fallbackToDestructiveMigration(true)
                .build()
        }

        single {
            Room.databaseBuilder(
                androidContext(),
                FavoriteMovieDatabase::class.java,
                FAVORITE_MOVIES_DATABASE
            )
                .fallbackToDestructiveMigration(true)
                .build()
        }

        single<PopularMoviesRemoteDataSource> { PopularMoviesRemoteDataSourceImp() }

        single<PopularMovieRepository> { PopularMovieRepositoryImp(get(), get()) }

        single<FindMovieRemoteDataSource> { FindMovieRemoteDataSourceImp() }

        single<FindMovieRepository> { FindMovieRepositoryImp(get()) }

        single { FindMovieUseCase(get()) }

        single<MovieDetailsRemoteDataSource> { MovieDetailsRemoteDataSourceImp() }

        single { get<FavoriteMovieDatabase>().favoriteMovieDao() }

        single<MovieDetailsRepository> { MovieDetailsRepositoryImp( get() ) }

        single { MovieDetailsUseCase(get()) }

        single { get<FavoriteMovieDatabase>().favoriteMovieDao() }

        single<FavoriteMoviesRepository> { FavoriteMoviesRepositoryImp(get()) }
    }
}
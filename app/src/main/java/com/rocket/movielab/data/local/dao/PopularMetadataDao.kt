package com.rocket.movielab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rocket.movielab.data.local.entity.PopularMetadata

@Dao
interface PopularMetadataDao {
    @Query("SELECT * FROM popular_metadata WHERE id = 1")
    suspend fun getPopularMetadata(): PopularMetadata?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMetadata(popularMetadata: PopularMetadata)
}

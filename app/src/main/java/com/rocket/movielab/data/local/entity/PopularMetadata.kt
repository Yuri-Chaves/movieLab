package com.rocket.movielab.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_metadata")
data class PopularMetadata(
    @PrimaryKey val id:Int = 1,
    val lastUpdated: Long = System.currentTimeMillis(),
)

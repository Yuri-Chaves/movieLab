package com.rocket.movielab.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val movieId: Int,
    val nextKey: Int?,
    val prevKey: Int?
)

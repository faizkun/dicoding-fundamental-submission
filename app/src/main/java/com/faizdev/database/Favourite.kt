package com.faizdev.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Favourite (
    @PrimaryKey val id: Int,
    @ColumnInfo("imageUrl") val imageUrl: String,
    @ColumnInfo("username") val username: String
    )

package com.faizdev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1, entities = [Favourite::class])
abstract class GitSearchDatabase : RoomDatabase(){
    abstract fun favouriteDao() : DatabaseDao

    companion object{
        @Volatile private var INSTANCE: GitSearchDatabase? = null
        fun getDatabase(context: Context): GitSearchDatabase{
            return INSTANCE?: synchronized(this){
                INSTANCE?: database(context).also{
                    INSTANCE = it
                }
            }
        }

        private fun database(context: Context): GitSearchDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                GitSearchDatabase::class.java,
            "gitsearch.db")
                .fallbackToDestructiveMigration()
                .build()
        }


    }
}
package com.example.sasukeapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sasukeapp.data.local.dao.HeroDao
import com.example.sasukeapp.data.local.dao.HeroRemoteKeyDao
import com.example.sasukeapp.domain.model.Hero
import com.example.sasukeapp.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class SasukeDatabase : RoomDatabase() {

    // Creating in memory database for testing
    companion object{
        fun create(context : Context, useInMemory : Boolean) : SasukeDatabase{
            val databaseBuilder = if(useInMemory){
                Room.inMemoryDatabaseBuilder(context, SasukeDatabase::class.java)
            }else{
                Room.databaseBuilder(context, SasukeDatabase::class.java, "test_database.db")
            }
            return databaseBuilder.fallbackToDestructiveMigration().build()
        }
    }

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao

}
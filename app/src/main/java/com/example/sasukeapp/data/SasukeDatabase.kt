package com.example.sasukeapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sasukeapp.data.local.DatabaseConverter
import com.example.sasukeapp.data.local.dao.HeroDao
import com.example.sasukeapp.data.local.dao.HeroRemoteKeyDao
import com.example.sasukeapp.domain.model.Hero
import com.example.sasukeapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class SasukeDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao

}
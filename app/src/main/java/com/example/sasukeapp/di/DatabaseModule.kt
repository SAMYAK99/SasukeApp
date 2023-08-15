package com.example.sasukeapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sasukeapp.data.local.SasukeDatabase
import com.example.sasukeapp.util.Constants.SASUKE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : SasukeDatabase{
        return Room.databaseBuilder(
            context  ,
            SasukeDatabase :: class.java ,
            SASUKE_DATABASE
        ).build()
    }
}
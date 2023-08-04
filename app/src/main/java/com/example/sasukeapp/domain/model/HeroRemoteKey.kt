package com.example.sasukeapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sasukeapp.util.Constants.HERO_REMOTE_KEY_DATABASE_TABLE

/*
* Remote KEYS are the Keys that remote mediation table uses to tell the backend server which data to load next
* */

@Entity(tableName = HERO_REMOTE_KEY_DATABASE_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
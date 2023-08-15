package com.example.sasukeapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sasukeapp.util.Constants.HERO_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(HERO_DATABASE_TABLE)
data class Hero(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
)


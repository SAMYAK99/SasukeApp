package com.example.sasukeapp.domain.repository

import com.example.sasukeapp.domain.model.Hero

interface LocalDataSource {

    suspend fun getSelectedHero(heroId : Int) : Hero
}
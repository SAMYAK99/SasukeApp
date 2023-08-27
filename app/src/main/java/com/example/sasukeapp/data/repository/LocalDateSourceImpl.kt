package com.example.sasukeapp.data.repository

import com.example.sasukeapp.data.local.SasukeDatabase
import com.example.sasukeapp.domain.model.Hero
import com.example.sasukeapp.domain.repository.LocalDataSource

class LocalDateSourceImpl(sasukeDatabase: SasukeDatabase) : LocalDataSource {

    private val heroDao = sasukeDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId)
    }
}

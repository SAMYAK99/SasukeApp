package com.example.sasukeapp.domain.usecases

import com.example.sasukeapp.data.repository.Repository
import com.example.sasukeapp.domain.model.Hero

class GetSelectedHeroUseCase(
   private val repository: Repository
) {

   suspend operator fun invoke(heroId : Int) : Hero {
       return repository.getSelectedHero(heroId)
   }

}
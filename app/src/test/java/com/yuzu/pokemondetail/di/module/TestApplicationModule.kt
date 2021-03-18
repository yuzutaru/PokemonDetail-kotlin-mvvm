package com.yuzu.pokemondetail.di.module

import com.yuzu.pokemondetail.PokemonDetailApplication
import com.yuzu.pokemondetail.model.api.PokemonAPI
import com.yuzu.pokemondetail.model.repository.PokemonRepository
import io.mockk.mockk

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

class TestApplicationModule(application: PokemonDetailApplication): AppModule(application) {
    override fun pokemonAPI(): PokemonAPI = mockk()
    override fun pokemonRepository(api: PokemonAPI): PokemonRepository = mockk()
}
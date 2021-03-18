package com.yuzu.pokemondetail.model.repository

import com.yuzu.pokemondetail.model.api.PokemonAPI
import com.yuzu.pokemondetail.model.data.Pokemon
import io.reactivex.Single

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

class PokemonRepositoryImpl(private val api: PokemonAPI): PokemonRepository {
    override fun pokemon(offset: Int, limit: Int): Single<Pokemon> {
        return api.pokemon(offset, limit)
    }
}
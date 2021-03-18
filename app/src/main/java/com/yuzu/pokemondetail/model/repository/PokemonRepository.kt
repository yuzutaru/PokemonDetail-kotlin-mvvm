package com.yuzu.pokemondetail.model.repository

import com.yuzu.pokemondetail.model.data.Pokemon
import io.reactivex.Single

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

interface PokemonRepository {
    fun pokemon(offset: Int, limit: String): Single<Pokemon>
}
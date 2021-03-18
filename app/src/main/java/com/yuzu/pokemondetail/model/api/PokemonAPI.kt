package com.yuzu.pokemondetail.model.api

import com.yuzu.pokemondetail.model.data.Pokemon
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

interface PokemonAPI {
    /**
     * Get List Pokemon
     * */
    @GET(value = "pokemon")
    fun pokemon(@Query("offset") offset: Int, @Query("limit") limit: String): Single<Pokemon>
}
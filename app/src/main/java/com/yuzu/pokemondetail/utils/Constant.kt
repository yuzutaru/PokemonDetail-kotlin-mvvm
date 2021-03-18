package com.yuzu.pokemondetail.utils

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

const val TIMEOUT_HTTP: String = "60"
const val RETRY_MAX = 3
const val RETRY_DELAY = 800L

const val BASE_URL = "https://pokeapi.co/api/v2/"
fun getImage(id: String): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
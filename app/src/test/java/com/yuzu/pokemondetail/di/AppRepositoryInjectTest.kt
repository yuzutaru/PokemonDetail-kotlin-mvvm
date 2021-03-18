package com.yuzu.pokemondetail.di

import com.yuzu.pokemondetail.PokemonDetailApplication
import com.yuzu.pokemondetail.di.component.DaggerTestApplicationComponent
import com.yuzu.pokemondetail.di.module.TestApplicationModule
import com.yuzu.pokemondetail.model.api.PokemonAPI
import com.yuzu.pokemondetail.model.data.Pokemon
import com.yuzu.pokemondetail.model.repository.PokemonRepository
import io.mockk.every
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

class AppRepositoryInjectTest {
    @Inject
    lateinit var api: PokemonAPI

    @Inject
    lateinit var repository: PokemonRepository

    @Before
    fun setUp() {
        val component = DaggerTestApplicationComponent.builder()
            .appModule(TestApplicationModule(PokemonDetailApplication()))
            .build()
        component.into(this)
    }

    @Test
    fun apiPokemonTest() {
        Assert.assertNotNull(api)
        every { api.pokemon(0, 10) } returns Single.just(Pokemon())
        val result = api.pokemon(0, 10)
        result.test().assertValue(Pokemon())
    }

    @Test
    fun repositoryPokemonTest() {
        Assert.assertNotNull(repository)
        every { repository.pokemon(0, 10) } returns Single.just(Pokemon())
        val result = repository.pokemon(0, 10)
        result.test().assertValue(Pokemon())
    }
}
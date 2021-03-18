package com.yuzu.pokemondetail.di.component

import android.app.Application
import com.yuzu.pokemondetail.di.module.AppModule
import com.yuzu.pokemondetail.model.api.PokemonAPI
import com.yuzu.pokemondetail.model.repository.PokemonRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: Application)

    //Pokemon API
    fun pokemonAPI(): PokemonAPI
    fun pokemonRepository(): PokemonRepository
}
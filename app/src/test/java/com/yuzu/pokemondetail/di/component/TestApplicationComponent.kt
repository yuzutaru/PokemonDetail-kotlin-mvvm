package com.yuzu.pokemondetail.di.component

import com.yuzu.pokemondetail.di.AppRepositoryInjectTest
import com.yuzu.pokemondetail.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

@Component(modules = [AppModule::class])
interface TestApplicationComponent {
    fun into(test: AppRepositoryInjectTest)
}
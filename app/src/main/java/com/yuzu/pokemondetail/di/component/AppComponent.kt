package com.yuzu.pokemondetail.di.component

import android.app.Application
import com.yuzu.pokemondetail.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: Application)
}
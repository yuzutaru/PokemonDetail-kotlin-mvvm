package com.yuzu.pokemondetail

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.yuzu.pokemondetail.di.component.AppComponent
import com.yuzu.pokemondetail.di.component.DaggerAppComponent
import com.yuzu.pokemondetail.di.module.AppModule

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

class PokemonDetailApplication: Application() {
    lateinit var component: AppComponent

    fun getAppComponent(): AppComponent {
        return component
    }

    companion object {
        lateinit var instance: PokemonDetailApplication private set
    }

    operator fun get(context: Context): PokemonDetailApplication {
        return context.applicationContext as PokemonDetailApplication
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // DI
        component = DaggerAppComponent.builder()
            .build()
        component.inject(this)
    }
}
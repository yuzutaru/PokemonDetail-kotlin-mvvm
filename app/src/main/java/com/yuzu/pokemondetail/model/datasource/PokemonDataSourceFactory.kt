package com.yuzu.pokemondetail.model.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yuzu.pokemondetail.model.data.Result
import com.yuzu.pokemondetail.model.repository.PokemonRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yustar Pramudana on 19/03/2021
 */

class PokemonDataSourceFactory(private val pokemonRepository: PokemonRepository, private val compositeDisposable: CompositeDisposable): DataSource.Factory<Int, Result>() {
    val pokemonDataSourceLiveData = MutableLiveData<PokemonDataSource>()

    override fun create(): DataSource<Int, Result> {
        val pokemonDataSource = PokemonDataSource(pokemonRepository, compositeDisposable)
        pokemonDataSourceLiveData.postValue(pokemonDataSource)
        return pokemonDataSource
    }
}
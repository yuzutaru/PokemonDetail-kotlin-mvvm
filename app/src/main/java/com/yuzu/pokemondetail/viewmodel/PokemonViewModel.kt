package com.yuzu.pokemondetail.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yuzu.pokemondetail.PokemonDetailApplication
import com.yuzu.pokemondetail.databinding.FragmentPokemonBinding
import com.yuzu.pokemondetail.model.State
import com.yuzu.pokemondetail.model.data.Result
import com.yuzu.pokemondetail.model.datasource.PokemonDataSource
import com.yuzu.pokemondetail.model.datasource.PokemonDataSourceFactory
import com.yuzu.pokemondetail.model.repository.PokemonRepository
import com.yuzu.pokemondetail.view.adapter.PokemonAdapter
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yustar Pramudana on 19/03/2021
 */

class PokemonViewModel: ViewModel() {
    private val LOG_TAG = "Movie"
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private val compositeDisposable = CompositeDisposable()
    private val pokemonRepository: PokemonRepository
    private val pokemonDataSourceFactory: PokemonDataSourceFactory

    var pokemonPagedList: LiveData<PagedList<Result>>
    private val pageSize = 5

    init {
        val appComponent = PokemonDetailApplication.instance.getAppComponent()
        pokemonRepository = appComponent.pokemonRepository()

        pokemonDataSourceFactory = PokemonDataSourceFactory(pokemonRepository, compositeDisposable)
        val config = PagedList.Config.Builder().setPageSize(pageSize).setInitialLoadSizeHint(pageSize).setEnablePlaceholders(false).build()
        pokemonPagedList = LivePagedListBuilder(pokemonDataSourceFactory, config).build()
    }

    fun retry() {
        pokemonDataSourceFactory!!.pokemonDataSourceLiveData.value?.retry()
    }

    fun getState(): LiveData<State> = Transformations.switchMap(
        pokemonDataSourceFactory!!.pokemonDataSourceLiveData,
        PokemonDataSource::state
    )

    private fun listIsEmpty(): Boolean {
        return pokemonPagedList.value?.isEmpty() ?: true
    }

    fun recyclerViewVisibility(binding: FragmentPokemonBinding, state: State, pokemonAdapter: PokemonAdapter) {
        if (listIsEmpty() && state == State.LOADING) {
            loading.value = true
            binding.recyclerview.visibility = View.GONE
            //binding.txtError.visibility = View.GONE

        } else if (listIsEmpty() && state == State.ERROR) {
            loading.value = false
            //binding.txtError.visibility = View.VISIBLE

        } else {
            loading.value = false
            binding.recyclerview.visibility = View.VISIBLE
            //binding.txtError.visibility = View.GONE
        }

        if (!listIsEmpty()) {
            pokemonAdapter.setState(state)
        }
    }
}
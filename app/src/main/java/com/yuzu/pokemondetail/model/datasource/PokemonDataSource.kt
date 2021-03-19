package com.yuzu.pokemondetail.model.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.yuzu.pokemondetail.model.State
import com.yuzu.pokemondetail.model.data.Result
import com.yuzu.pokemondetail.model.repository.PokemonRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yustar Pramudana on 18/03/2021
 */

class PokemonDataSource(private val pokemonRepository: PokemonRepository, private val compositeDisposable: CompositeDisposable): PageKeyedDataSource<Int, Result>() {
    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            pokemonRepository.pokemon(0, 10)
                .subscribe(
                    { response ->
                        if (!response.results.isNullOrEmpty()) {
                            updateState(State.DONE)
                            callback.onResult(response.results!!,null,10)

                        } else {
                            updateState(State.ERROR)
                        }
                    },
                    {
                        updateState(State.ERROR)
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            pokemonRepository.pokemon(params.key, 10)
                .subscribe(
                    { response ->
                        if (!response.results.isNullOrEmpty()) {
                            updateState(State.DONE)
                            callback.onResult(response.results!!, params.key + 10)

                        } else {
                            updateState(State.ERROR)
                        }
                    },
                    {
                        updateState(State.ERROR)
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        TODO("Not yet implemented")
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}
package com.yuzu.pokemondetail.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.yuzu.pokemondetail.databinding.FragmentPokemonBinding
import com.yuzu.pokemondetail.view.adapter.PokemonAdapter
import com.yuzu.pokemondetail.viewmodel.PokemonViewModel
import java.util.*

/**
 * Created by Yustar Pramudana on 19/03/2021
 */

class PokemonFragment: Fragment() {
    private val LOG_TAG = "Movie"
    private lateinit var binding: FragmentPokemonBinding
    private lateinit var viewModel: PokemonViewModel

    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPokemonBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapter()
        initState()
    }

    private fun initAdapter() {
        pokemonAdapter = PokemonAdapter(viewModel) { viewModel.retry() }
        binding.recyclerview.adapter = pokemonAdapter
        viewModel.pokemonPagedList.observe(viewLifecycleOwner, {
            try {
                Log.e("Paging ", "PageAll" + it.size)
                try {
                    //to prevent animation recyclerview when change the list
                    binding.recyclerview.itemAnimator = null
                    (Objects.requireNonNull(binding.recyclerview.getItemAnimator()) as SimpleItemAnimator).supportsChangeAnimations = false
                } catch (e: Exception) {
                }
                pokemonAdapter.submitList(it)
            } catch (e: Exception) {
            }
        })
    }

    private fun initState() {
        viewModel.getState().observe(viewLifecycleOwner, { state ->
            viewModel.recyclerViewVisibility(binding, state, pokemonAdapter)
            //footerBinding.progressBar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            //footerBinding.txtError.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
        })
    }
}
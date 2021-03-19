package com.yuzu.pokemondetail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yuzu.pokemondetail.R
import com.yuzu.pokemondetail.model.State
import com.yuzu.pokemondetail.model.data.Result
import com.yuzu.pokemondetail.viewmodel.PokemonViewModel

/**
 * Created by Yustar Pramudana on 12/03/2021
 */

class PokemonAdapter(private val viewModel: PokemonViewModel, private val retry: () -> Unit): PagedListAdapter<Result, RecyclerView.ViewHolder>(UserDiffCallback) {
    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2
    private var state = State.LOADING

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_pokemon, parent, false)
        val skeleton = inflater.inflate(R.layout.skeleton_pokemon, parent, false)

        return if (viewType == DATA_VIEW_TYPE) PokemonViewHolder(viewModel, view).create(parent) else SkeletonViewHolder(skeleton).create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as PokemonViewHolder).bind(getItem(position))
        else (holder as SkeletonViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}
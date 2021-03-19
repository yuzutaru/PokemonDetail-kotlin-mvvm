package com.yuzu.pokemondetail.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuzu.pokemondetail.R
import com.yuzu.pokemondetail.databinding.SkeletonPokemonBinding
import com.yuzu.pokemondetail.model.State

/**
 * Created by Yustar Pramudana on 12/03/2021
 */

class SkeletonViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = SkeletonPokemonBinding.bind(view)

    fun create(parent: ViewGroup): SkeletonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.skeleton_pokemon, parent, false)
        return SkeletonViewHolder(view)
    }

    fun bind(status: State?) {
        Log.e("skeleton", "bind:State = $status")
        binding.skeletonLayout.visibility = if (status == State.LOADING) View.VISIBLE else View.GONE
        binding.txtError.visibility = if (status == State.ERROR || status == State.DONE) View.VISIBLE else View.GONE

        binding.skeletonConstraint.isEnabled = status == State.LOADING
    }
}
package com.yuzu.pokemondetail.view.activity

import android.os.Bundle
import com.yuzu.pokemondetail.R
import com.yuzu.pokemondetail.view.PokemonFragment

/**
 * Created by Yustar Pramudana on 19/03/2021
 */

class MainActivity: BaseViewActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_content,
                    PokemonFragment()
                ).commit()
        }
    }
}
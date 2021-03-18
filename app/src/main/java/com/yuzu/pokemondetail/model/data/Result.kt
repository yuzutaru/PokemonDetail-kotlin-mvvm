package com.yuzu.pokemondetail.model.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null
): Parcelable
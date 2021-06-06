package com.example.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class TitleEntityData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val title: String
)

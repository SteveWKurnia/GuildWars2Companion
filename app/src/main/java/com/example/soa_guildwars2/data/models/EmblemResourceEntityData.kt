package com.example.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class EmblemResourceEntityData(
        @SerializedName("id")
        val id: String,
        @SerializedName("layers")
        val layers: List< String>
)

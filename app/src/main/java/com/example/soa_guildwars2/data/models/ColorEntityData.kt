package com.example.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class ColorEntityData(
        @SerializedName("name")
        val name: String,
        @SerializedName("base_rgb")
        val base_rgb: List<Int>
)

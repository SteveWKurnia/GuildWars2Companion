package com.example.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class EmblemDetailEntityData(
        @SerializedName("id")
        var id: String,
        @SerializedName("colors")
        var colors: List<Int>
)

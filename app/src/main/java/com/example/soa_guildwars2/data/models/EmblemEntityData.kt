package com.example.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class EmblemEntityData(
        @SerializedName("background")
        var background: EmblemDetailEntityData,
        @SerializedName("foreground")
        var foreground: EmblemDetailEntityData
)

package com.example.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class GuildEntityData(
        @SerializedName("level")
        var lvl: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("tag")
        var tag: String,
        @SerializedName("emblem")
        var emblemEntityData: EmblemEntityData,
        @SerializedName("motd")
        var motd: String
        )
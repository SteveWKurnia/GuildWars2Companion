package com.example.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class CharacterEntityData(
    @SerializedName("name")
    var name: String,
    @SerializedName("race")
    var race: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("profession")
    var profession: String,
    @SerializedName("level")
    var level: Int,
    @SerializedName("title")
    var titleId: String = ""
)

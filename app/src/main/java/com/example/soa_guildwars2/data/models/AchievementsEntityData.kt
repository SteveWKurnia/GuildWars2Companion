package com.example.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class AchievementsEntityData (
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("requirement")
    var description: String
)
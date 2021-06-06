package com.example.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp
import java.util.*

data class GuildMemberEntityData(
        @SerializedName("name")
        var name: String,
        @SerializedName("rank")
        var rank: String,
        @SerializedName("joined")
        var time: Date
)

package com.example.soa_guildwars2.domain.datamodel

import com.google.gson.annotations.SerializedName

data class AccountDataModel(
        var id: String,
        var name: String,
        var age: Int,
        var guilds: List<String>
        )

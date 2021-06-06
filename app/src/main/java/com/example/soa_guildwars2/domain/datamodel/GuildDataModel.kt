package com.example.soa_guildwars2.domain.datamodel

data class GuildDataModel(
        var id: String,
        var lvl: Int,
        var name: String,
        var tag: String,
        var foregroundEmblemId: String,
        var backgroundEmblemId: String,
        var motd: String
)

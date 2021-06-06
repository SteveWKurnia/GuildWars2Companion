package com.example.soa_guildwars2.app.home.worldboss

data class WorldBossModel(
        var world_boss: String,
        var isDone: Boolean,
        var timer: List<String>
)

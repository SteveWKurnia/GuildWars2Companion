package stephenwk.com.soa_guildwars2.domain.datamodel

data class WorldBossCompleteDataModel(
        val world_boss: String,
        val isDone: Boolean,
        val timer: List<String>
)

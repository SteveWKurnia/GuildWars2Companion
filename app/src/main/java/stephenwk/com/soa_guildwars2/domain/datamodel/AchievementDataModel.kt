package stephenwk.com.soa_guildwars2.domain.datamodel

data class AchievementDataModel(
        var id: String,
        var name: String,
        var description: String,
        var minLvl: Int,
        var maxLvl: Int
        )

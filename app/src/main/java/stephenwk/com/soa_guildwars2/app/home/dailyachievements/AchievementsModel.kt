package stephenwk.com.soa_guildwars2.app.home.dailyachievements

data class AchievementsModel(
        var id: String,
        var name: String,
        var description: String,
        var minLvl: Int,
        var maxLvl: Int,
        var isExpanded: Boolean = false
        )
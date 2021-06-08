package stephenwk.com.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class DailyAchievementsIndividualEntityData(
        @SerializedName("id")
        var id: String,
        @SerializedName("level")
        var level: LevelEntityData,
        @SerializedName("required_access")
        var access: List<String>
)
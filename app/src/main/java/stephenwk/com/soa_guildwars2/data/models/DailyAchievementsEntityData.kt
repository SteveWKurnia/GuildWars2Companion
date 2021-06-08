package stephenwk.com.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class DailyAchievementsEntityData(
        @SerializedName("pve")
        var daily: List<DailyAchievementsIndividualEntityData>
)

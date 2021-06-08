package stephenwk.com.soa_guildwars2.data.mapper

import com.example.soa_guildwars2.data.models.AchievementsEntityData
import com.example.soa_guildwars2.data.models.DailyAchievementsIndividualEntityData
import com.example.soa_guildwars2.domain.datamodel.AchievementDataModel
import javax.inject.Inject

class AchievementDataMapper @Inject constructor(){
    fun map(achievementsEntityData: List<AchievementsEntityData>,
            dailyAchievementsIndividualEntityData: List<DailyAchievementsIndividualEntityData>)
    : List<AchievementDataModel> {
        val list = mutableListOf<AchievementDataModel>()
        achievementsEntityData.forEachIndexed { index, element ->
            list.add(AchievementDataModel(
                    id = element.id,
                    name = element.name,
                    description = element.description,
                    minLvl = dailyAchievementsIndividualEntityData[index].level.min,
                    maxLvl = dailyAchievementsIndividualEntityData[index].level.max
            ))
        }
        return list
    }
}
package com.example.soa_guildwars2.app.home.dailyachievements

import com.example.soa_guildwars2.domain.datamodel.AchievementDataModel
import javax.inject.Inject

class AchievementsModelMapper @Inject constructor(){
    fun map(data: List<AchievementDataModel>): List<AchievementsModel> {
        val list = mutableListOf<AchievementsModel>()
        data.forEach {
            list.add(AchievementsModel(it.id, it.name, it.description, it.minLvl, it.maxLvl))
        }
        return list
    }
}
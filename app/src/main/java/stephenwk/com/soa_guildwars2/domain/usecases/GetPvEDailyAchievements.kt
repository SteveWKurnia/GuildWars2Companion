package stephenwk.com.soa_guildwars2.domain.usecases

import stephenwk.com.soa_guildwars2.domain.datamodel.AchievementDataModel
import stephenwk.com.soa_guildwars2.domain.HomeRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPvEDailyAchievements @Inject constructor(
        private val homeRepository: HomeRepository
) {
    fun execute(): Observable<List<AchievementDataModel>> = homeRepository.getAchievement()
}
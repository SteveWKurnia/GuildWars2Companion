package stephenwk.com.soa_guildwars2.domain.usecases

import stephenwk.com.soa_guildwars2.domain.HomeRepository
import stephenwk.com.soa_guildwars2.domain.datamodel.WorldBossCompleteDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetAllWorldBoss @Inject constructor(
        private val homeRepository: HomeRepository
) {
    fun execute(): Observable<List<WorldBossCompleteDataModel>> = homeRepository.getAllWordBosses()
}
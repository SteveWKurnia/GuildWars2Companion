package stephenwk.com.soa_guildwars2.domain.usecases

import stephenwk.com.soa_guildwars2.domain.datamodel.AccountDataModel
import stephenwk.com.soa_guildwars2.domain.HomeRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetAccountData @Inject constructor(
        private val homeRepository: HomeRepository
){

    fun execute(): Observable<AccountDataModel> = homeRepository.getAccountData()

}
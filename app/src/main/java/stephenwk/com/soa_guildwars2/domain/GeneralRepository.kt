package stephenwk.com.soa_guildwars2.domain

import stephenwk.com.soa_guildwars2.domain.datamodel.AccountDataModel
import io.reactivex.rxjava3.core.Observable

interface GeneralRepository {

    fun checkKeyAuthenticity(api_key: String): Observable<AccountDataModel>

    fun saveApiKey(api_key: String)

    fun getApiKey(): Observable<String>

    fun removeApiKey()
}
package com.example.soa_guildwars2.data.repository

import com.example.soa_guildwars2.data.RetrofitInstance
import com.example.soa_guildwars2.data.localdata.LocalApiKeySharedPref
import com.example.soa_guildwars2.data.mapper.AccountDataMapper
import com.example.soa_guildwars2.data.networkdata.AccountMainData
import com.example.soa_guildwars2.domain.GeneralRepository
import com.example.soa_guildwars2.domain.datamodel.AccountDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GeneralEntityRepository @Inject constructor(
        private val accountDataMapper: AccountDataMapper,
        private val localApiKeySharedPref: LocalApiKeySharedPref
): GeneralRepository {

    private val retrofit = RetrofitInstance.retrofit

    override fun checkKeyAuthenticity(api_key: String): Observable<AccountDataModel> {
        return retrofit.create(AccountMainData::class.java).getAccountMainData(api_key).map {
            accountDataMapper.map(accountEntityData = it)
        }
    }

    override fun saveApiKey(api_key: String) {
        localApiKeySharedPref.saveApiKey(api_key)
    }

    override fun getApiKey(): Observable<String> {
        return Observable.just(localApiKeySharedPref.getApiKey())
    }

    override fun removeApiKey() {
        localApiKeySharedPref.removeApiKey()
    }
}
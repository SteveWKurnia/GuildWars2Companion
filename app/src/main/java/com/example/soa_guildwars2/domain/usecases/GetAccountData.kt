package com.example.soa_guildwars2.domain.usecases

import com.example.soa_guildwars2.domain.datamodel.AccountDataModel
import com.example.soa_guildwars2.domain.HomeRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetAccountData @Inject constructor(
        private val homeRepository: HomeRepository
){

    fun execute(): Observable<AccountDataModel> = homeRepository.getAccountData()

}
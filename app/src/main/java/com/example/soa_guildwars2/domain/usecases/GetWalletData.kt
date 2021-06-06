package com.example.soa_guildwars2.domain.usecases

import com.example.soa_guildwars2.domain.HomeRepository
import javax.inject.Inject

class GetWalletData @Inject constructor(
        private val homeRepository: HomeRepository
) {
    fun execute() = homeRepository.getAccountWallet()
}
package com.example.soa_guildwars2.domain.usecases

import com.example.soa_guildwars2.domain.GuildRepository
import com.example.soa_guildwars2.domain.datamodel.GuildDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetGuildMainData @Inject constructor(
        private val guildRepository: GuildRepository
) {
    fun execute(guildId: String): Observable<GuildDataModel> = guildRepository.getGuildData(guildId)
}
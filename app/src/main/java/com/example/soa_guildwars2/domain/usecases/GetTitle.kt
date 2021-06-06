package com.example.soa_guildwars2.domain.usecases

import com.example.soa_guildwars2.domain.MiscellaneousRepository
import com.example.soa_guildwars2.domain.datamodel.TitleDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetTitle @Inject constructor(
    private val miscellaneousRepository: MiscellaneousRepository
) {
    fun execute(id: String): Observable<TitleDataModel> = miscellaneousRepository.getTitle(id)
}
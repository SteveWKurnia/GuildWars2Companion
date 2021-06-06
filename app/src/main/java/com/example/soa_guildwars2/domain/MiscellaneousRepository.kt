package com.example.soa_guildwars2.domain

import com.example.soa_guildwars2.domain.datamodel.ColorDataModel
import com.example.soa_guildwars2.domain.datamodel.TitleDataModel
import io.reactivex.rxjava3.core.Observable

interface MiscellaneousRepository {
    fun getColor(id: String): Observable<ColorDataModel>

    fun getTitle(id: String): Observable<TitleDataModel>
}
package com.example.soa_guildwars2.data.repository

import com.example.soa_guildwars2.data.RetrofitInstance
import com.example.soa_guildwars2.data.networkdata.MiscellaneousData
import com.example.soa_guildwars2.domain.MiscellaneousRepository
import com.example.soa_guildwars2.domain.datamodel.ColorDataModel
import com.example.soa_guildwars2.domain.datamodel.TitleDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MiscellaneousEntityRepository @Inject constructor(): MiscellaneousRepository {
    override fun getColor(id: String): Observable<ColorDataModel> {
        return RetrofitInstance.retrofit.create(MiscellaneousData::class.java).getColor(id).map {
            val data = ColorDataModel(it.name, it.base_rgb)
            data
        }
    }

    override fun getTitle(id: String): Observable<TitleDataModel> {
        return RetrofitInstance.retrofit.create(MiscellaneousData::class.java).getTitle(id).map {
            val data = TitleDataModel(it.id, it.title)
            data
        }
    }
}
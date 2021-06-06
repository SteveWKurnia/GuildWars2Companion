package com.example.soa_guildwars2.data.networkdata

import com.example.soa_guildwars2.data.models.ColorEntityData
import com.example.soa_guildwars2.data.models.TitleEntityData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MiscellaneousData {
    @GET("colors/{id}")
    fun getColor(@Path("id") id: String): Observable<ColorEntityData>

    @GET("titles/{id}")
    fun getTitle(@Path("id") id: String): Observable<TitleEntityData>
}
package com.example.soa_guildwars2.data.networkdata

import com.example.soa_guildwars2.data.models.AchievementsEntityData
import com.example.soa_guildwars2.data.models.DailyAchievementsEntityData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AchievementsData {

    @GET("achievements/daily")
    fun getDailyAchievements(): Observable<DailyAchievementsEntityData>

    @GET("achievements")
    fun getAchievementsById(@Query("ids") ids: String): Observable<List<AchievementsEntityData>>

}
package com.example.soa_guildwars2.data.networkdata

import com.example.soa_guildwars2.data.models.EmblemResourceEntityData
import com.example.soa_guildwars2.data.models.GuildEntityData
import com.example.soa_guildwars2.data.models.GuildMemberEntityData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GuildData {

    @GET("guild/{id}")
    fun getGuildData(
            @Path("id") id:String,
            @Query("access_token") api_key: String
    ): Observable<GuildEntityData>

    @GET("guild/{id}/members")
    fun getGuildMemberData(
            @Path("id") id:String,
            @Query("access_token") api_key: String
    ): Observable<List<GuildMemberEntityData>>

    @GET("emblem/{ground}")
    fun getEmblemData(
            @Path("ground") groundLevel: String,
            @Query("ids") id: String
    ): Observable<List<EmblemResourceEntityData>>


    @GET("emblem/foregrounds")
    fun getFGEmblemData(
            @Query("ids") id: String
    ): Observable<List<EmblemResourceEntityData>>


    @GET("emblem/backgrounds")
    fun getBGEmblemData(
            @Query("ids") id: String
    ): Observable<List<EmblemResourceEntityData>>
}
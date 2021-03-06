package com.example.soa_guildwars2.domain

import com.example.soa_guildwars2.data.models.EmblemResourceEntityData
import com.example.soa_guildwars2.domain.datamodel.EmblemResourceDataModel
import com.example.soa_guildwars2.domain.datamodel.GuildDataModel
import com.example.soa_guildwars2.domain.datamodel.GuildMemberDataModel
import io.reactivex.rxjava3.core.Observable

interface GuildRepository {

    fun getAllGuildData(): Observable<List<GuildDataModel>>

    fun getGuildData(guildId: String): Observable<GuildDataModel>

    fun getGuildMemberData(): Observable<List<GuildMemberDataModel>>

    fun getEmblemResource(groundLevel: String, id: String): Observable<EmblemResourceDataModel>
}
package com.example.soa_guildwars2.domain

import com.example.soa_guildwars2.domain.datamodel.CharacterDataModel
import io.reactivex.rxjava3.core.Observable

interface CharacterRepository {
    fun getCharactersData(): Observable<List<CharacterDataModel>>

    fun getCharacterList(): Observable<List<String>>
    fun getCharacterDetailData(name: String): Observable<CharacterDataModel>
}
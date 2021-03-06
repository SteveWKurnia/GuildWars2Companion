package com.example.soa_guildwars2.domain.usecases

import com.example.soa_guildwars2.domain.CharacterRepository
import com.example.soa_guildwars2.domain.datamodel.CharacterDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetCharactersData @Inject constructor(
        private val characterRepository: CharacterRepository
) {
    fun execute(): Observable<List<CharacterDataModel>> = characterRepository.getCharactersData()
}
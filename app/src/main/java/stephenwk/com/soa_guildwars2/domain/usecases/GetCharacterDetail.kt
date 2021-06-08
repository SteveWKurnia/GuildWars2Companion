package stephenwk.com.soa_guildwars2.domain.usecases

import stephenwk.com.soa_guildwars2.domain.CharacterRepository
import stephenwk.com.soa_guildwars2.domain.datamodel.CharacterDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetCharacterDetail @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    fun execute(name: String): Observable<CharacterDataModel> = characterRepository.getCharacterDetailData(name)
}
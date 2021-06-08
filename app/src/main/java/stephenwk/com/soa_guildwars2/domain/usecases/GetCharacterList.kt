package stephenwk.com.soa_guildwars2.domain.usecases

import com.example.soa_guildwars2.domain.CharacterRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetCharacterList @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    fun execute(): Observable<List<String>> = characterRepository.getCharacterList()
}
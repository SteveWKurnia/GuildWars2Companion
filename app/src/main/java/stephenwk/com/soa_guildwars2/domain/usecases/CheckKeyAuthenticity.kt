package stephenwk.com.soa_guildwars2.domain.usecases

import com.example.soa_guildwars2.domain.GeneralRepository
import javax.inject.Inject

class CheckKeyAuthenticity @Inject constructor(
        private val generalRepository: GeneralRepository
) {
    fun execute(api_key: String) = generalRepository.checkKeyAuthenticity(api_key)
}
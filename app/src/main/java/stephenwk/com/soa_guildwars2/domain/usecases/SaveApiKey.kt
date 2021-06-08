package stephenwk.com.soa_guildwars2.domain.usecases

import stephenwk.com.soa_guildwars2.domain.GeneralRepository
import javax.inject.Inject

class SaveApiKey @Inject constructor(
    private val generalRepository: GeneralRepository
) {
    fun execute(api_key: String) = generalRepository.saveApiKey(api_key)
}
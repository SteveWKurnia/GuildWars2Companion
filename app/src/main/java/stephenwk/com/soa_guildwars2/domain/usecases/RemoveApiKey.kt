package stephenwk.com.soa_guildwars2.domain.usecases

import stephenwk.com.soa_guildwars2.domain.GeneralRepository
import javax.inject.Inject

class RemoveApiKey @Inject constructor(
        private val generalRepository: GeneralRepository
) {
    fun execute() = generalRepository.removeApiKey()
}
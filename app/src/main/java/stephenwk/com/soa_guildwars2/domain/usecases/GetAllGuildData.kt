package stephenwk.com.soa_guildwars2.domain.usecases

import stephenwk.com.soa_guildwars2.domain.GuildRepository
import javax.inject.Inject

class GetAllGuildData @Inject constructor(
    private val guildRepository: GuildRepository
) {
    fun execute() = guildRepository.getAllGuildData()
}
package stephenwk.com.soa_guildwars2.domain.usecases

import stephenwk.com.soa_guildwars2.domain.GuildRepository
import stephenwk.com.soa_guildwars2.domain.datamodel.EmblemResourceDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetEmblemResource @Inject constructor(
        private val guildRepository: GuildRepository
) {
    fun execute(groundLevel: String, id: String): Observable<EmblemResourceDataModel> = guildRepository.getEmblemResource(groundLevel, id)
}
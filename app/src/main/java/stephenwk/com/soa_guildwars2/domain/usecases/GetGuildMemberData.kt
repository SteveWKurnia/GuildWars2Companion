package stephenwk.com.soa_guildwars2.domain.usecases

import com.example.soa_guildwars2.domain.GuildRepository
import com.example.soa_guildwars2.domain.datamodel.GuildMemberDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetGuildMemberData @Inject constructor(
        private val guildRepository: GuildRepository
) {
    fun execute(): Observable<List<GuildMemberDataModel>> = guildRepository.getGuildMemberData()
}
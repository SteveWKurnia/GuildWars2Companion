package stephenwk.com.soa_guildwars2.domain.usecases

import com.example.soa_guildwars2.domain.MiscellaneousRepository
import com.example.soa_guildwars2.domain.datamodel.ColorDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetColorData @Inject constructor(
        private val miscellaneousRepository: MiscellaneousRepository
) {
    fun execute(id: String): Observable<ColorDataModel> = miscellaneousRepository.getColor(id)
}
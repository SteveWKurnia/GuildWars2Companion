package stephenwk.com.soa_guildwars2.domain

import stephenwk.com.soa_guildwars2.domain.datamodel.ColorDataModel
import stephenwk.com.soa_guildwars2.domain.datamodel.TitleDataModel
import io.reactivex.rxjava3.core.Observable

interface MiscellaneousRepository {
    fun getColor(id: String): Observable<ColorDataModel>

    fun getTitle(id: String): Observable<TitleDataModel>
}
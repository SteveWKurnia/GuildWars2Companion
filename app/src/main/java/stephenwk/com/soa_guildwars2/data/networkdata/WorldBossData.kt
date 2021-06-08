package stephenwk.com.soa_guildwars2.data.networkdata

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WorldBossData {

    @GET("worldbosses")
    fun getAllWorldBossData(): Observable<List<String>>

    @GET("account/worldbosses")
    fun getAccountDailyWorldBoss(@Query("access_token") api_key: String):
            Observable<List<String>>

}
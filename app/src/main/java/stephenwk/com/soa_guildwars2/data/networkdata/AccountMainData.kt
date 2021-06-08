package stephenwk.com.soa_guildwars2.data.networkdata

import stephenwk.com.soa_guildwars2.data.models.AccountEntityData
import stephenwk.com.soa_guildwars2.data.models.WalletEntityData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountMainData {

    @GET("account")
    fun getAccountMainData(@Query("access_token") api_key: String):
            Observable<AccountEntityData>

    @GET("account/wallet")
    fun getAccountWallet(@Query("access_token") api_key: String):
            Observable<List<WalletEntityData>>

}
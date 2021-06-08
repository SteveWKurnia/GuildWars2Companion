package stephenwk.com.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class WalletEntityData (
        @SerializedName("id")
        var id: Int,
        @SerializedName("value")
        var value: Int
        )
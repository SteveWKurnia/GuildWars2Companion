package stephenwk.com.soa_guildwars2.data.models

import com.google.gson.annotations.SerializedName

data class AccountEntityData(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("age")
    var age: Int,
    @SerializedName("guilds")
    var guilds: List<String>
)

package stephenwk.com.soa_guildwars2.domain.datamodel

data class AccountDataModel(
        var id: String,
        var name: String,
        var age: Int,
        var guilds: List<String>
        )

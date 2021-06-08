package stephenwk.com.soa_guildwars2.domain.datamodel

data class CharacterDataModel(
    val name: String = "",
    val race: String = "",
    var gender: String = "",
    val profession: String = "",
    val level: Int = 0,
    val title: String? = ""
)
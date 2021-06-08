package stephenwk.com.soa_guildwars2.app.character

data class CharacterModel(
    val name: String = "",
    val race: String = "",
    var gender: String = "",
    val profession: String = "",
    val level: Int = 0,
    val title: String? = ""
)

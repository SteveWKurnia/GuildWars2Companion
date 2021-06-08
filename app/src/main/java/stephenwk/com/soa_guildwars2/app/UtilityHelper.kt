package stephenwk.com.soa_guildwars2.app

import java.util.*

object UtilityHelper {

    fun String.cleanWorldBossUnderscore(): String = split("_").joinToString(" ") { it.capitalize(Locale.ROOT) }
}
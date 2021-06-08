package stephenwk.com.soa_guildwars2.data.localdata

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalApiKeySharedPref @Inject constructor(
    @ApplicationContext context: Context
){

    private val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    fun saveApiKey(api_key: String) = with(sharedPref.edit()) {
        putString(API_KEY, api_key)
        commit()
    }

    fun getApiKey(): String? = sharedPref.getString(API_KEY, "")

    fun removeApiKey() = sharedPref.edit().remove(API_KEY).commit()

    companion object {
        val SHARED_PREF_KEY = "SHARED_PREF_KEY"

        val API_KEY = "API_KEY"
    }
}
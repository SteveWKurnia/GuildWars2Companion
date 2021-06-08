package stephenwk.com.soa_guildwars2.data.networkdata

import stephenwk.com.soa_guildwars2.data.models.CharacterEntityData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterData {

    @GET("characters")
    fun getCharactersData(@Query("access_token")api_key: String): Observable<List<String>>

    @GET("characters/{name}/core")
    fun getCharacterDetail(@Path("name")name: String, @Query("access_token")api_key: String): Observable<CharacterEntityData>

}
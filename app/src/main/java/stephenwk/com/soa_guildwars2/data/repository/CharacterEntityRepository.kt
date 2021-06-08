package stephenwk.com.soa_guildwars2.data.repository

import android.util.Log
import com.example.soa_guildwars2.BuildConfig
import com.example.soa_guildwars2.data.RetrofitInstance
import com.example.soa_guildwars2.data.localdata.LocalApiKeySharedPref
import com.example.soa_guildwars2.data.networkdata.CharacterData
import com.example.soa_guildwars2.domain.CharacterRepository
import com.example.soa_guildwars2.domain.MiscellaneousRepository
import com.example.soa_guildwars2.domain.datamodel.CharacterDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CharacterEntityRepository @Inject constructor(
    private val miscellaneousRepository: MiscellaneousRepository,
    private val localApiKeySharedPref: LocalApiKeySharedPref
): CharacterRepository {

    private val retrofit = RetrofitInstance.retrofit.create(CharacterData::class.java)

    override fun getCharactersData(): Observable<List<CharacterDataModel>> {
        return retrofit.getCharactersData(localApiKeySharedPref.getApiKey()!!).flatMap { characterList -> //List of String
            val cdm = mutableListOf<CharacterDataModel>()
            characterList.forEach { characterName -> //String
                retrofit.getCharacterDetail(name = characterName, api_key = BuildConfig.API_KEY).flatMap { characterEntityData ->
                    miscellaneousRepository.getTitle(characterEntityData.titleId).map { title ->
                        Log.d("TestingCharacter", characterEntityData.name)
                        cdm.add(
                            CharacterDataModel(
                                name = characterEntityData.name,
                                gender = characterEntityData.gender,
                                profession = characterEntityData.profession,
                                level = characterEntityData.level,
                                title = title.title,
                                race = characterEntityData.race
                            )
                        )
                    }
                }.blockingFirst()
            }
            Observable.fromArray(cdm)
        }
    }

    override fun getCharacterList(): Observable<List<String>> {
        return retrofit.getCharactersData(localApiKeySharedPref.getApiKey()!!)
    }

    override fun getCharacterDetailData(name: String): Observable<CharacterDataModel> {
        return retrofit.getCharacterDetail(name, localApiKeySharedPref.getApiKey()!!).map { characterEntityData ->
            val data = CharacterDataModel(
                name = characterEntityData.name,
                gender = characterEntityData.gender,
                profession = characterEntityData.profession,
                level = characterEntityData.level,
                title = characterEntityData.titleId,
                race = characterEntityData.race
            )
            data
        }
    }

}
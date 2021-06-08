package stephenwk.com.soa_guildwars2.data.repository

import com.example.soa_guildwars2.BuildConfig
import com.example.soa_guildwars2.data.RetrofitInstance
import com.example.soa_guildwars2.data.localdata.LocalApiKeySharedPref
import com.example.soa_guildwars2.data.models.AccountEntityData
import com.example.soa_guildwars2.data.networkdata.AccountMainData
import com.example.soa_guildwars2.data.networkdata.GuildData
import com.example.soa_guildwars2.domain.GuildRepository
import com.example.soa_guildwars2.domain.datamodel.EmblemResourceDataModel
import com.example.soa_guildwars2.domain.datamodel.GuildDataModel
import com.example.soa_guildwars2.domain.datamodel.GuildMemberDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GuildEntityRepository @Inject constructor(
    private val localApiKeySharedPref: LocalApiKeySharedPref
): GuildRepository {

    private val retrofit = RetrofitInstance.retrofit

    private fun getAccountMainData(): Observable<AccountEntityData>{
        return retrofit.create(AccountMainData::class.java).getAccountMainData(localApiKeySharedPref.getApiKey()!!)
    }

    fun getAllGuildDataTest(): Observable<List<GuildDataModel>> {
        return getAccountMainData().map {
            val listOfGuild = mutableListOf<GuildDataModel>()
            repeat(it.guilds.size) { guildIdx ->
                retrofit.create(GuildData::class.java).getGuildData(id = it.guilds[guildIdx], localApiKeySharedPref.getApiKey()!!).map { entityData ->
                    retrofit.create(GuildData::class.java).getFGEmblemData(entityData.emblemEntityData.foreground.id).zipWith(
                            retrofit.create(GuildData::class.java).getFGEmblemData(entityData.emblemEntityData.background.id),
                            {
                                fg, bg ->
                                listOfGuild.add(GuildDataModel(
                                        id = it.guilds[guildIdx],
                                        name = entityData.name,
                                        lvl = entityData.lvl,
                                        tag = entityData.tag,
                                        foregroundEmblemId = fg[0].layers[0],
                                        backgroundEmblemId = bg[0].layers[0],
                                        motd = entityData.motd
                                ))
                            }
                    ).blockingFirst()
                }
            }
            listOfGuild
        }
    }

    override fun getAllGuildData(): Observable<List<GuildDataModel>> {
        return getAccountMainData().map {
            val listOfGuild = mutableListOf<GuildDataModel>()
            repeat(it.guilds.size) { guildIdx ->
                retrofit.create(GuildData::class.java).getGuildData(id = it.guilds[guildIdx], localApiKeySharedPref.getApiKey()!!).map { entityData ->
                    retrofit.create(GuildData::class.java).getFGEmblemData(entityData.emblemEntityData.foreground.id).zipWith(
                            retrofit.create(GuildData::class.java).getBGEmblemData(entityData.emblemEntityData.background.id),
                            {
                                fg, bg ->
                                listOfGuild.add(GuildDataModel(
                                        id = it.guilds[guildIdx],
                                        name = entityData.name,
                                        lvl = entityData.lvl,
                                        tag = entityData.tag,
                                        foregroundEmblemId = fg[0].layers[0],
                                        backgroundEmblemId = bg[0].layers[0],
                                        motd = entityData.motd
                                ))
                            }
                    ).blockingFirst()
                }.blockingFirst()
            }
            listOfGuild
        }
    }

//    override fun getAllGuildData(): Observable<List<GuildDataModel>> {
//        return getAccountMainData().map {
//            val listOfGuild = mutableListOf<GuildDataModel>()
//            repeat(it.guilds.size) { guildIdx ->
//                retrofit.create(GuildData::class.java).getGuildData(id = it.guilds[guildIdx], localApiKeySharedPref.getApiKey()!!).map { entityData ->
//                    listOfGuild.add(GuildDataModel(
//                        id = it.guilds[guildIdx],
//                        name = entityData.name,
//                        lvl = entityData.lvl,
//                        tag = entityData.tag,
//                        foregroundEmblemId = entityData.emblemEntityData.foreground.id,
//                        backgroundEmblemId = entityData.emblemEntityData.background.id,
//                        motd = entityData.motd
//                    ))
//                }.blockingFirst()
//            }
//            listOfGuild
//        }
//    }

    override fun getGuildData(guildId: String): Observable<GuildDataModel> {
        return getAccountMainData().flatMap {
                    retrofit.create(GuildData::class.java).getGuildData(id = guildId, BuildConfig.API_KEY).map { entityData ->
                        val dataModel = GuildDataModel(
                                id = guildId,
                                name = entityData.name,
                                lvl = entityData.lvl,
                                tag = entityData.tag,
                                foregroundEmblemId = entityData.emblemEntityData.foreground.id,
                                backgroundEmblemId = entityData.emblemEntityData.background.id,
                                motd = entityData.motd
                        )
                        dataModel
                    }
                }
    }

    override fun getGuildMemberData(): Observable<List<GuildMemberDataModel>> {
        return getAccountMainData().flatMap {
            retrofit.create(GuildData::class.java).getGuildMemberData(id = it.guilds[0], BuildConfig.API_KEY).map { data ->
                val list = mutableListOf<GuildMemberDataModel>()
                data.forEach {  memberInfo ->
                    list.add(GuildMemberDataModel(memberInfo.name, memberInfo.rank, memberInfo.time))
                }
                list
            }
        }
    }

    override fun getEmblemResource(groundLevel: String, id: String): Observable<EmblemResourceDataModel> {
        return retrofit.create(GuildData::class.java).getEmblemData(groundLevel, id).map {
            val data = EmblemResourceDataModel(
                    it[0].id,
                    it[0].layers
            )
            data
        }
    }


}
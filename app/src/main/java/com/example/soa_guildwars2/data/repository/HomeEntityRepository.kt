package com.example.soa_guildwars2.data.repository

import android.util.Log
import com.example.soa_guildwars2.BuildConfig
import com.example.soa_guildwars2.data.RetrofitInstance
import com.example.soa_guildwars2.data.localdata.LocalApiKeySharedPref
import com.example.soa_guildwars2.data.localdata.LocalWorldBossTimer
import com.example.soa_guildwars2.data.mapper.AccountDataMapper
import com.example.soa_guildwars2.data.mapper.AchievementDataMapper
import com.example.soa_guildwars2.data.models.DailyAchievementsIndividualEntityData
import com.example.soa_guildwars2.data.networkdata.AccountMainData
import com.example.soa_guildwars2.data.networkdata.AchievementsData
import com.example.soa_guildwars2.data.networkdata.WorldBossData
import com.example.soa_guildwars2.domain.HomeRepository
import com.example.soa_guildwars2.domain.datamodel.*
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HomeEntityRepository @Inject constructor(
        private val accountDataMapper: AccountDataMapper,
        private val achievementDataMapper: AchievementDataMapper,
        private val localApiKeySharedPref: LocalApiKeySharedPref
): HomeRepository{

    private val retrofit = RetrofitInstance.retrofit

    override fun getAccountData(): Observable<AccountDataModel> {
        return retrofit.create(AccountMainData::class.java).getAccountMainData(localApiKeySharedPref.getApiKey()!!).map {
            accountDataMapper.map(accountEntityData = it)
        }
    }

    override fun getAccountWallet(): Observable<List<WalletDataModel>> {
        return retrofit.create(AccountMainData::class.java).getAccountWallet(localApiKeySharedPref.getApiKey()!!).map { walletList ->
            val wdm = mutableListOf<WalletDataModel>()
            walletList.forEach {
                wdm.add(WalletDataModel(id = it.id, value = it.value))
            }
            wdm
        }
    }

    override fun getAllWordBosses(): Observable<List<WorldBossCompleteDataModel>> {
        return  retrofit.create(WorldBossData::class.java).getAllWorldBossData().zipWith(
                retrofit.create(WorldBossData::class.java).getAccountDailyWorldBoss(api_key = localApiKeySharedPref.getApiKey()!!),
                { wbList, wbAcc ->
                    val data = mutableListOf<WorldBossDataModel>()
                    wbList.forEach { wbName ->
                        data.add(WorldBossDataModel(
                                world_boss = wbName,
                                isDone = wbAcc.contains(wbName)
                        ))
                    }
                    data
                }
        ).zipWith(
                Observable.fromArray(LocalWorldBossTimer.getWBTime()),
                { wbList, wbTimer ->
                    wbList.forEach {
                        Log.d("TestingWB", it.world_boss)
                    }
                    val data = mutableListOf<WorldBossCompleteDataModel>()
                    wbTimer.forEachIndexed { idx, element ->
                        element.forEach {
                            Log.d("TestingWB", "WB: ${wbList[idx].world_boss}, Timer: $it")
                        }
                        data.add(WorldBossCompleteDataModel(
                                world_boss = wbList[idx].world_boss,
                                isDone = wbList[idx].isDone,
                                timer = element
                        ))
                    }
                    data
                }
        )
    }

    override fun getTodayPvEDailyAchievements(): Observable<List<String>> {
        return retrofit.create(AchievementsData::class.java).getDailyAchievements().map { dailyAchievements ->
            val idList = mutableListOf<String>()
            dailyAchievements.daily.forEach { daily ->
                idList.add(daily.id)
            }
            idList
        }
    }

    override fun getAchievement(): Observable<List<AchievementDataModel>> {
        return retrofit.create(AchievementsData::class.java).getDailyAchievements().map { dailyAchievements ->
            val data = mutableListOf<DailyAchievementsIndividualEntityData>()
            dailyAchievements.daily.forEach { daily ->
                data.add(DailyAchievementsIndividualEntityData(daily.id, daily.level, daily.access))
            }
            data
        }.flatMap {
            val idList = mutableListOf<String>()
            it.forEach { daily ->
                idList.add(daily.id)
            }
            val ids = idList.joinToString(",")
            retrofit.create(AchievementsData::class.java).getAchievementsById(ids).map { achievement ->
                achievementDataMapper.map(achievement, it)
            }
        }
    }

}
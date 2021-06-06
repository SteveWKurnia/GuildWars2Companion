package com.example.soa_guildwars2.domain

import com.example.soa_guildwars2.domain.datamodel.*
import io.reactivex.rxjava3.core.Observable

interface HomeRepository {

    fun getAccountData(): Observable<AccountDataModel>

    fun getAccountWallet(): Observable<List<WalletDataModel>>

    fun getAllWordBosses(): Observable<List<WorldBossCompleteDataModel>>

    fun getTodayPvEDailyAchievements(): Observable<List<String>>

    fun getAchievement(): Observable<List<AchievementDataModel>>
}
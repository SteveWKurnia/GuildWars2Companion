package com.example.soa_guildwars2.app.home

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soa_guildwars2.app.home.dailyachievements.AchievementsModel
import com.example.soa_guildwars2.app.home.dailyachievements.AchievementsModelMapper
import com.example.soa_guildwars2.app.home.worldboss.WorldBossModel
import com.example.soa_guildwars2.domain.datamodel.*
import com.example.soa_guildwars2.domain.usecases.*
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel @ViewModelInject constructor(
        @ApplicationContext context: Context,
        private val removeApiKey: RemoveApiKey,
        private val getApiKey: GetApiKey,
        private val getAccountData: GetAccountData,
        private val getWalletData: GetWalletData,
        private val getAllWorldBoss: GetAllWorldBoss,
        private val getPvEDailyAchievements: GetPvEDailyAchievements,
        private val achievementsModelMapper: AchievementsModelMapper
) : ViewModel() {

    private val _accountName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val _gold: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    private val _silver: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    private val _copper: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    private val _gem: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    private val _karma: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    private val _completeWorldBoss: MutableLiveData<List<WorldBossModel>> by lazy {
        MutableLiveData<List<WorldBossModel>>()
    }
    private val _dailyAchievements: MutableLiveData<List<AchievementsModel>> by lazy {
        MutableLiveData<List<AchievementsModel>>()
    }

    val gold: LiveData<Int>
        get() = _gold

    val accountName: LiveData<String>
        get() = _accountName

    val silver: LiveData<Int>
        get() = _silver

    val copper: LiveData<Int>
        get() = _copper

    val gem: LiveData<Int>
        get() = _gem

    val karma: LiveData<Int>
        get() = _karma

    val completeWorldBoss: LiveData<List<WorldBossModel>>
        get() = _completeWorldBoss

    val dailyAchievements: LiveData<List<AchievementsModel>>
        get() = _dailyAchievements

    fun getAccountName() {
        getAccountData.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object: DisposableObserver<AccountDataModel>() {
                    override fun onNext(accountDataModel: AccountDataModel) {
                        _accountName.postValue(accountDataModel.name)
                    }

                    override fun onError(e: Throwable?) {
                        //no-implementation
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getAccountGold() {
        getWalletData.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<List<WalletDataModel>>() {
                    override fun onNext(walletDataModel: List<WalletDataModel>) {
                        _gold.postValue(walletDataModel[0].value / 10000)
                    }

                    override fun onError(e: Throwable?) {
                        //no-implementation
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getAccountSilver() {
        getWalletData.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<List<WalletDataModel>>() {
                    override fun onNext(walletDataModel: List<WalletDataModel>) {
                        val totalGold = walletDataModel[0].value.toString()
                        when {
                            totalGold.toInt() >= 10000 -> _silver.postValue(totalGold.takeLast(4).take(2).toInt())
                            totalGold.toInt() >= 100 ->_silver.postValue(totalGold.take(1).toInt())
                            else -> _silver.postValue(0)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        //no-implementation
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getAccountCopper() {
        getWalletData.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<List<WalletDataModel>>() {
                    override fun onNext(walletDataModel: List<WalletDataModel>) {
                        val totalGold = walletDataModel[0].value.toString()
                        _copper.postValue(totalGold.takeLast(2).toInt())
                    }

                    override fun onError(e: Throwable?) {
                        //no-implementation
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getAccountGem() {
        getWalletData.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<List<WalletDataModel>>() {
                    override fun onNext(walletDataModel: List<WalletDataModel>) {
                        _gem.postValue(walletDataModel[3].value)
                    }

                    override fun onError(e: Throwable?) {
                        //no-implementation
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getAccountKarma() {
        getWalletData.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<List<WalletDataModel>>() {
                    override fun onNext(walletDataModel: List<WalletDataModel>) {
                        _karma.postValue(walletDataModel[1].value)
                    }

                    override fun onError(e: Throwable?) {
                        //no-implementation
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getAllWorldBoss() {
        getAllWorldBoss.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object: DisposableObserver<List<WorldBossCompleteDataModel>>() {
                    override fun onNext(t: List<WorldBossCompleteDataModel>?) {
                        _completeWorldBoss.postValue(t?.map {
                                WorldBossModel(it.world_boss, it.isDone, it.timer)
                            }
                        )
                    }

                    override fun onError(e: Throwable?) {
                        TODO("Not yet implemented")
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getDailyAchievements() {
        getPvEDailyAchievements.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object: DisposableObserver<List<AchievementDataModel>>() {
                    override fun onNext(t: List<AchievementDataModel>?) {
                        _dailyAchievements.postValue(t?.let { achievementsModelMapper.map(it) })
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("TestingDaily", e?.message.toString())
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun removeApiKey() = removeApiKey.execute()
}
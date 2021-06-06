package com.example.soa_guildwars2.app.guild.guilddetail

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soa_guildwars2.domain.datamodel.ColorDataModel
import com.example.soa_guildwars2.domain.datamodel.EmblemResourceDataModel
import com.example.soa_guildwars2.domain.datamodel.GuildDataModel
import com.example.soa_guildwars2.domain.datamodel.GuildMemberDataModel
import com.example.soa_guildwars2.domain.usecases.GetColorData
import com.example.soa_guildwars2.domain.usecases.GetEmblemResource
import com.example.soa_guildwars2.domain.usecases.GetGuildMainData
import com.example.soa_guildwars2.domain.usecases.GetGuildMemberData
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class GuildDetailViewModel @ViewModelInject constructor(
        @ApplicationContext context: Context,
        private val getGuildMemberData: GetGuildMemberData,
        private val getGuildMainData: GetGuildMainData,
        private val getEmblemResource: GetEmblemResource
): ViewModel() {

    private val _guildName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val _guildTag: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val _guildMotd: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val _guildMembers: MutableLiveData<List<GuildMemberModel>> by lazy {
        MutableLiveData<List<GuildMemberModel>>()
    }

    private val _guildForegroundEmblem: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val _guildBackgroundEmblem: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val guildTag: LiveData<String>
        get() = _guildTag

    val guildName: LiveData<String>
        get() = _guildName

    val guildMotd: LiveData<String>
        get() = _guildMotd

    val guildMembers: LiveData<List<GuildMemberModel>>
        get() = _guildMembers

    val guildBackgroundEmblem: LiveData<String>
        get() = _guildBackgroundEmblem

    val guildForegroundEmblem: LiveData<String>
        get() = _guildForegroundEmblem

    fun getGuildData(guildId: String) {
        getGuildMainData.execute(guildId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object: DisposableObserver<GuildDataModel>() {
                    override fun onNext(t: GuildDataModel?) {
                        _guildName.postValue(t?.name?.ifEmpty { "Blank" })
                        _guildTag.postValue(t?.tag?.ifEmpty { "Blank" })
                        _guildMotd.postValue(t?.motd?.ifEmpty { "Blank" })
                        t?.foregroundEmblemId?.ifEmpty { "" }?.let { getEmblemResource("foregrounds", it) }
                        t?.backgroundEmblemId?.ifEmpty { "" }?.let { getEmblemResource("backgrounds", it) }
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("TestingGuild", e?.message.toString())
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getGuildMemberData() {
        getGuildMemberData.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object: DisposableObserver<List<GuildMemberDataModel>>() {
                    override fun onNext(data: List<GuildMemberDataModel>?) {
                        _guildMembers.postValue(data?.map {
                            GuildMemberModel(it.name, it.rank, it.time)
                        })
                    }

                    override fun onError(e: Throwable?) {
                        TODO("Not yet implemented")
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getEmblemResource(groundLevel: String, id: String) {
        getEmblemResource.execute(groundLevel, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<EmblemResourceDataModel>() {
                    override fun onNext(t: EmblemResourceDataModel?) {
                        when(groundLevel) {
                            "foregrounds" -> _guildForegroundEmblem.postValue(t?.layers?.get(0)?.ifEmpty { "" })
                            "backgrounds" -> _guildBackgroundEmblem.postValue(t?.layers?.get(0)?.ifEmpty { "" })
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("TestingGuild", e?.message.toString())
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

}
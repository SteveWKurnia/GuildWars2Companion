package com.example.soa_guildwars2.app.guild

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soa_guildwars2.domain.datamodel.EmblemResourceDataModel
import com.example.soa_guildwars2.domain.datamodel.GuildDataModel
import com.example.soa_guildwars2.domain.usecases.GetAllGuildData
import com.example.soa_guildwars2.domain.usecases.GetEmblemResource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class GuildViewModel @ViewModelInject constructor(
    private val getAllGuildData: GetAllGuildData
): ViewModel() {

    private val _allGuildData: MutableLiveData<List<GuildDataModel>> by lazy {
        MutableLiveData<List<GuildDataModel>>()
    }
    val allGuildData: LiveData<List<GuildDataModel>>
        get() = _allGuildData

    fun getAllGuildData() {
        getAllGuildData.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableObserver<List<GuildDataModel>>() {
                override fun onNext(t: List<GuildDataModel>?) {
                    val a = mutableListOf<GuildDataModel>()
                    repeat(2) {
                        if (t != null) {
                            a.addAll(t)
                        }
                    }
                    _allGuildData.postValue(a)
                }

                override fun onError(e: Throwable?) {
//                    TODO("Not yet implemented")
                }

                override fun onComplete() {
//                    TODO("Not yet implemented")
                }

            })
    }

}
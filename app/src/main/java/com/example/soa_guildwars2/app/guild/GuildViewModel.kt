package com.example.soa_guildwars2.app.guild

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soa_guildwars2.domain.datamodel.GuildDataModel
import com.example.soa_guildwars2.domain.usecases.GetAllGuildData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GuildViewModel @Inject constructor(
    private val getAllGuildData: GetAllGuildData
) : ViewModel() {

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
                    _allGuildData.postValue(t)
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
package com.example.soa_guildwars2.app.apikey

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soa_guildwars2.domain.datamodel.AccountDataModel
import com.example.soa_guildwars2.domain.usecases.CheckKeyAuthenticity
import com.example.soa_guildwars2.domain.usecases.SaveApiKey
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ApiKeyViewModel @Inject constructor(
    private val saveApiKeyUseCase: SaveApiKey,
    private val checkKeyAuthenticity: CheckKeyAuthenticity
) : ViewModel() {

    private val _authenticity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val authenticity: LiveData<Boolean>
        get() = _authenticity

    fun saveApiKey(api_key: String) {
        saveApiKeyUseCase.execute(api_key)
    }

    fun checkApiKeyAuthenticity(api_key: String) {
        checkKeyAuthenticity.execute(api_key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableObserver<AccountDataModel>() {
                override fun onNext(t: AccountDataModel?) {
                    _authenticity.postValue(true)
                    saveApiKey(api_key)
                }

                override fun onError(e: Throwable?) {
                    _authenticity.postValue(false)
                }

                override fun onComplete() {
//                    TODO("Not yet implemented")
                }
            })
    }

}
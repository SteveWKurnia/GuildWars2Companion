package com.example.soa_guildwars2.app.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soa_guildwars2.domain.usecases.GetApiKey
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getApiKey: GetApiKey
) : ViewModel() {

    private val _api_key: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val api_key: LiveData<String>
        get() = _api_key

    fun getApiKey() {
        getApiKey.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableObserver<String>() {
                override fun onNext(t: String?) {
                    _api_key.postValue(t)
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
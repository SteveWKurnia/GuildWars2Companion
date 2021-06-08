package stephenwk.com.soa_guildwars2.app.character

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import stephenwk.com.soa_guildwars2.domain.datamodel.CharacterDataModel
import stephenwk.com.soa_guildwars2.domain.datamodel.TitleDataModel
import stephenwk.com.soa_guildwars2.domain.usecases.GetCharacterDetail
import stephenwk.com.soa_guildwars2.domain.usecases.GetCharacterList
import stephenwk.com.soa_guildwars2.domain.usecases.GetCharactersData
import stephenwk.com.soa_guildwars2.domain.usecases.GetTitle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class CharacterViewModel @ViewModelInject constructor(
        private val getCharactersData: GetCharactersData,
        private val getCharacterList: GetCharacterList,
        private val getCharacterDetail: GetCharacterDetail,
        private val getTitle: GetTitle
): ViewModel() {

    private val _characterData: MutableLiveData<List<CharacterModel>> by lazy {
        MutableLiveData<List<CharacterModel>>()
    }

    private val _characterDetails: MutableLiveData<MutableList<CharacterModel>> by lazy {
        MutableLiveData<MutableList<CharacterModel>>()
    }

    val characterDetails: LiveData<MutableList<CharacterModel>>
        get() = _characterDetails

    val characterData: LiveData<List<CharacterModel>>
        get() = _characterData

    fun getCharactersData() {
        getCharactersData.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object: DisposableObserver<List<CharacterDataModel>>() {
                    override fun onNext(data: List<CharacterDataModel>?) {
                        val charList = mutableListOf<CharacterModel>()
                        data?.forEach {
                            Log.d("TestingCharacterInVM", it.name)
                            charList.add(
                                CharacterModel(
                                    name = it.name,
                                    gender = it.gender,
                                    race = it.race,
                                    profession = it.profession,
                                    level = it.level,
                                    title = it.title
                            ))
                        }
                        _characterData.postValue(charList)
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("TestingCharacter", e?.message.toString())
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

    fun getCharacterList() {
        getCharacterList.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: DisposableObserver<List<String>>() {
                override fun onNext(data: List<String>?) {
                    data?.forEach {
                        getCharacterDetail(it)
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.d("TestingCharacterInList", e?.message.toString())
                }

                override fun onComplete() {
                    //no-implementation
                }

            })
    }

    fun getCharacterDetail(name: String) {
        getCharacterDetail.execute(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: DisposableObserver<CharacterDataModel>() {
                override fun onNext(data: CharacterDataModel?) {
                    data?.let {
                        getTitle(it)
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.d("TestingCharacterDetail", e?.message.toString())
                }

                override fun onComplete() {
                    //no-implementation
                }

            })
    }

    fun getTitle(characterModel: CharacterDataModel) {
        var title = ""
        if (characterModel.title != null) {
            title = characterModel.title
        }
        if (title == "") {
            getTitle.execute(title)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(object: DisposableObserver<TitleDataModel>() {
                        override fun onNext(data: TitleDataModel?) {
                            //no-implementation
                        }

                        override fun onError(e: Throwable?) {
                            Log.d("TestingCharacterInList", e?.message.toString())
                            val temp = _characterDetails.value ?: mutableListOf()
                            temp.add(CharacterModel(
                                    name = characterModel.name,
                                    gender = characterModel.gender,
                                    race = characterModel.race,
                                    profession = characterModel.profession,
                                    level = characterModel.level,
                                    title = "No title."
                            ))
                            _characterDetails.postValue(temp)
                        }

                        override fun onComplete() {
//                            TODO("Not yet implemented")
                        }

                    })
        } else {
            getTitle.execute(title)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(object: DisposableObserver<TitleDataModel>() {
                        override fun onNext(data: TitleDataModel?) {
                            val temp = _characterDetails.value ?: mutableListOf()
                            data?.let {
                                temp.add(CharacterModel(
                                        name = characterModel.name,
                                        gender = characterModel.gender,
                                        race = characterModel.race,
                                        profession = characterModel.profession,
                                        level = characterModel.level,
                                        title = data.title
                                ))
                                _characterDetails.postValue(temp)
                            }
                        }

                        override fun onError(e: Throwable?) {
                            Log.d("TestingCharacterInList", e?.message.toString())
                        }

                        override fun onComplete() {
//                            TODO("Not yet implemented")
                        }

                    })
        }
    }
}

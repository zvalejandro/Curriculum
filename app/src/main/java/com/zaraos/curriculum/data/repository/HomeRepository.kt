package com.zaraos.curriculum.data.repository

import com.zaraos.curriculum.R
import com.zaraos.curriculum.data.source.AppSample
import com.zaraos.curriculum.data.source.Resource
import com.zaraos.curriculum.data.webservice.ApiService
import com.zaraos.curriculum.domain.entity.UserEntity
import com.zaraos.curriculum.utils.ResourceUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class HomeRepository @Inject constructor(val apiService: ApiService) {
    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    fun getUserInformation(callback: (Resource<UserEntity>?) -> Unit) {
        val username = AppSample.USERNAME
        callback.invoke(Resource.loading(null))
        val disposable = apiService.getUserInformation(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ resource ->
                if (resource != null && (resource.code in 200..299)) {
                    resource.data?.let { user ->
                        callback.invoke(Resource.success(user))
                    } ?: run {
                        callback.invoke(Resource.error(ResourceUtils.getString(R.string.msg_error), null))
                    }
                } else {
                    callback.invoke(Resource.error(ResourceUtils.getString(R.string.msg_error), null))
                }
            }, {
                it?.printStackTrace()
                callback.invoke(Resource.error(ResourceUtils.getString(R.string.msg_error), null))
            })
        allCompositeDisposable.add(disposable)
    }
}
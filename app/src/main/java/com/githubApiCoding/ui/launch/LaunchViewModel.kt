package com.githubApiCoding.ui.launch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.githubApiCoding.app.SearchApplication
import com.githubApiCoding.ui.ext.StateError
import com.githubApiCoding.ui.ext.StateIdle
import com.githubApiCoding.ui.ext.StateLiveData
import com.githubApiCoding.ui.ext.StateProgress
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Set launch screen delay and loads resources (Dagger components) in parallel
 */
class LaunchViewModel(application: Application) : AndroidViewModel(application) {

    private val launchDelay = 1L // show launch screen at least n seconds
    private val delayTimeUnit = TimeUnit.SECONDS

    private var subscription: Subscription? = null
    val state = StateLiveData(StateProgress)

    /**
     * Init Dagger application component and notify ready with [launchDelay]
     */
    fun launch() {
        val delay = Observable.timer(launchDelay, delayTimeUnit)
        val launch = Observable.just(getApplication<SearchApplication>())
                .map { it.appComponent }.map { 1L } // build Dagger app component while Launch screen
        subscription = Observable.zip(delay, launch, { _, _ -> StateIdle })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ state.value = it }, { state.value = StateError(it) })
    }

    override fun onCleared() {
        super.onCleared()
        subscription?.unsubscribe()
    }

}

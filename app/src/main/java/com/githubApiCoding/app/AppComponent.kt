package com.githubApiCoding.app

import com.githubApiCoding.api.ApiModule
import com.githubApiCoding.dao.DaoSession
import com.githubApiCoding.datamodel.UserLocalModel
import com.githubApiCoding.datamodel.UserRemoteModel
import dagger.Component
import javax.inject.Singleton

/**
 * Provide Application scope dependencies
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface AppComponent {

    fun daoSession(): DaoSession

    fun userLocalModel(): UserLocalModel

    fun userRemoteModel(): UserRemoteModel

}
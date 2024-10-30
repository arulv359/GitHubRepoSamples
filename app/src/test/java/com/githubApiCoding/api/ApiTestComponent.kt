package com.githubApiCoding.api

import com.githubApiCoding.datamodel.UserRemoteModelTest
import dagger.Component
import javax.inject.Singleton

/**
 * Provide dependencies for [UserRemoteModelTest]
 */
@Singleton
@Component(modules = [ApiModule::class])
interface ApiTestComponent {

    fun inject(test: UserRemoteModelTest)

}
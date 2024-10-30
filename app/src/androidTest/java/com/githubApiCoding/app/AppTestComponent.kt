package com.githubApiCoding.app

import com.githubApiCoding.datamodel.UserLocalModelTest
import com.githubApiCoding.ui.activity.ViewScope
import dagger.Component

/**
 * Provide dependencies for [UserLocalModelTest]
 */
@ViewScope
@Component(dependencies = arrayOf(AppComponent::class))
interface AppTestComponent {

    fun inject(test: UserLocalModelTest)

}
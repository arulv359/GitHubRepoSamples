package com.githubApiCoding.ui.activity

import com.githubApiCoding.app.AppComponent
import com.githubApiCoding.ui.details.DetailsFragment
import com.githubApiCoding.ui.search.SearchFragment
import dagger.Component

/**
 * Provide [ViewScope] dependencies based on [AppComponent]
 */
@ViewScope
@Component(dependencies = arrayOf(AppComponent::class))
interface MainComponent {

    fun inject(searchFragment: SearchFragment)

    fun inject(detailsFragment: DetailsFragment)

}
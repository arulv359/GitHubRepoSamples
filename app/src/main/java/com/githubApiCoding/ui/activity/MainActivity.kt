package com.githubApiCoding.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.githubApiCoding.R
import com.githubApiCoding.app.SearchApplication
import com.githubApiCoding.ui.ext.showLaunchFragment

class MainActivity : FragmentActivity() {

    val mainComponent: MainComponent by lazy { buildMainComponent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) showLaunchFragment()
    }

    private fun buildMainComponent(): MainComponent = DaggerMainComponent.builder()
            .appComponent((application as SearchApplication).appComponent)
            .build()

}

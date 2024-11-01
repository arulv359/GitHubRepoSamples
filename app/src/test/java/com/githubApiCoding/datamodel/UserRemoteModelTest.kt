package com.githubApiCoding.datamodel

import com.githubApiCoding.api.ApiModule
import com.githubApiCoding.api.DaggerApiTestComponent
import com.githubApiCoding.model.ProfileResponse
import com.githubApiCoding.model.RepoEntry
import com.githubApiCoding.model.UserEntry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import rx.observers.TestSubscriber
import javax.inject.Inject

/**
 * Test for [UserRemoteModel]
 */
class UserRemoteModelTest {

    @Inject lateinit var userRemoteModel: UserRemoteModel

    private val login = "antonshapovalov"

    @Before
    fun setUp() = DaggerApiTestComponent.builder()
            .apiModule(ApiModule())
            .build()
            .inject(this)

    @Test
    fun getUsers() {
        val testSubscriber = TestSubscriber<List<UserEntry>>()
        userRemoteModel.getUsers(login)
                .doOnNext {
                    Assert.assertTrue(it.isNotEmpty())
                    System.out.println(it[0])
                }
                .subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
        testSubscriber.assertUnsubscribed()
    }

    @Test
    fun getProfile() {
        val testSubscriber = TestSubscriber<ProfileResponse>()
        userRemoteModel.getProfile(login)
                .doOnNext {
                    Assert.assertTrue(it.name.isNotEmpty())
                    System.out.println(it)
                }
                .subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
        testSubscriber.assertUnsubscribed()
    }

    @Test
    fun getRepos() {
        val testSubscriber = TestSubscriber<List<RepoEntry>>()
        userRemoteModel.getRepos(login)
                .doOnNext {
                    Assert.assertTrue(it.isNotEmpty())
                    Assert.assertTrue(it[0].name.isNotEmpty())
                    it.forEach { System.out.println(it) }
                }
                .subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
        testSubscriber.assertUnsubscribed()
    }

}
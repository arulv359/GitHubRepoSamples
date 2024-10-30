package com.githubApiCoding.datamodel

import com.githubApiCoding.api.ApiService
import com.githubApiCoding.model.ProfileResponse
import com.githubApiCoding.model.RepoEntry
import com.githubApiCoding.model.UserEntry
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provide User data from [ApiService]
 */
@Singleton
class UserRemoteModel @Inject constructor() {

    @Inject lateinit var apiService: ApiService

    fun getUsers(query: String): Observable<List<UserEntry>> = apiService
            .getUsers(query)
            .map { if (it.isSuccessful) it.body()?.items else emptyList() }

    fun getProfile(login: String): Observable<ProfileResponse> = apiService
            .getProfile(login)
            .map { if (it.isSuccessful) it.body() else throw RuntimeException("Unable get Profile info") }

    fun getRepos(login: String): Observable<List<RepoEntry>> = apiService
            .getRepos(login)
            .map { if (it.isSuccessful) it.body() else throw RuntimeException("Unable get Repositories info") }

}
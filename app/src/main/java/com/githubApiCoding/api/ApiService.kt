package com.githubApiCoding.api

import com.githubApiCoding.model.ProfileResponse
import com.githubApiCoding.model.RepoEntry
import com.githubApiCoding.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * GitHub API retrofit service
 */
interface ApiService {

    @GET("search/users")
    fun getUsers(@Query("q") query: String): Observable<Response<UsersResponse>>

    @GET("users/{login}")
    fun getProfile(@Path("login") login: String): Observable<Response<ProfileResponse>>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): Observable<Response<List<RepoEntry>>>

}
package com.faizdev.fundamentalsubmission.API

import com.faizdev.fundamentalsubmission.response.DetailResponse
import com.faizdev.fundamentalsubmission.response.FollowerListResponse
import com.faizdev.fundamentalsubmission.response.FollowingListResponse
import com.faizdev.fundamentalsubmission.response.SearchResponse
import com.faizdev.fundamentalsubmission.response.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApi  {
    companion object {
        const val API_KEY = "token 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"

        fun create(): GitApi {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitApi::class.java)
        }
    }

    @GET("users")
    suspend fun Users(
        @Header("Authorization") key: String = API_KEY
    ): UserResponse

    @GET("users/{username}")
    suspend fun getDetailDatauser(
        @Path("username") username: String?,
        @Header("Authorization") key: String = API_KEY
    ): DetailResponse

    @GET("users/{username}/followers")
    suspend fun getFollowersByUsername(
        @Path("username") username: String?,
        @Header("Authorization") key: String = API_KEY
    ): FollowerListResponse

    @GET("users/{username}/following")
    suspend fun getFollowingByUsername(
        @Path("username") username: String?,
        @Header("Authorization") key: String = API_KEY
    ): FollowingListResponse

    @GET("search/users")
    suspend fun searchUserByUsername(
        @Query("q") username: String?,
        @Header("Authorization") key: String = API_KEY
    ): SearchResponse

}
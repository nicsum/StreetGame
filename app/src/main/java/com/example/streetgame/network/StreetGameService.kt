package com.example.streetgame.network

import com.example.streetgame.BuildConfig.API_URL
import com.example.streetgame.game.Record
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface StreetGameService {


    @GET("/getResults")
    suspend fun getResults(
        @Query("mode") mode: String,
        @Query("map_id") mapId: Int
    ): Response<List<Record>>



    @FormUrlEncoded
    @POST("/postResults")
    suspend fun postResults(
        @Field("mode") mode: String,
        @Field("username") user: String,
        @Field("map_id") mapId: Int,
        @Field("score") score: Int,
        @Field("count_task") countTasks: Int
    ): Response<PostResultsResponce>


}

data class PostResultsResponce(
    val id: Int,
    val score: Int,
    val updated: Boolean
)


fun createStreetGameService(): StreetGameService{

    val  client =  OkHttpClient.Builder().build()

    val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    return retrofit.create(StreetGameService::class.java)
}
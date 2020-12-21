package ru.kazantsev.eventsandposters.data.api

import retrofit2.Response
import retrofit2.http.GET
import ru.kazantsev.eventsandposters.model.Event
import ru.kazantsev.eventsandposters.model.Post

interface ApiService {


    @GET("/v1/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/v1/events")
    suspend fun getEvents(): Response<List<Event>>

    companion object {
        const val API_URL = "https://dm88q10ezc.execute-api.us-east-1.amazonaws.com"
    }
}
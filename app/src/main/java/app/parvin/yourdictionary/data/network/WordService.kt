package app.parvin.yourdictionary.data.network

import app.parvin.yourdictionary.model.network.WordResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WordService {

    @GET("{word}")
    suspend fun getWord(@Path("word") word: String): List<WordResponse>?
}
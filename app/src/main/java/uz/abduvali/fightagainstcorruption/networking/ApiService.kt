package uz.abduvali.fightagainstcorruption.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.abduvali.fightagainstcorruption.models.NewData

interface ApiService {

    @GET("v2/everything?apiKey=c024a77545414618b9d7bda726bb32de")
    suspend fun getNews(
        @Query("q") query: String
    ): Response<NewData>
}
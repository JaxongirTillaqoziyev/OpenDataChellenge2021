package uz.abduvali.fightagainstcorruption.networking

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    private const val baseUrl = "https://newsapi.org/"

    private fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val apiService = getRetrofit().create(ApiService::class.java)
}
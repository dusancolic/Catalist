package com.example.catalist.api

import com.example.catalist.api.model.CatData
import com.example.catalist.api.model.Image
import retrofit2.http.GET

interface CatApi {
    @GET("breeds")
    suspend fun getAllCats(): List<CatData>
//    @GET("breeds/{breed_id}")
//    suspend fun getCat(): List<CatData>
//
//    @GET("images/{image_id}")
//    suspend fun getImage(): List<Image>


}
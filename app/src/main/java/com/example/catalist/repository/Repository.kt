package com.example.catalist.repository

import com.example.catalist.api.CatApi
import com.example.catalist.api.model.CatData
import com.example.catalist.networking.retrofit
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

object Repository {
    private var mutableData = SampleData.toMutableList()
    private val catApi : CatApi = retrofit.create(CatApi::class.java)

    fun allData() : List<CatData> = mutableData

    fun getById(id: String) : CatData? {
        return mutableData.find { it.id == id }
    }
    suspend fun loadCats() : List<CatData> {
        delay(2.seconds)
        val data = catApi.getAllCats()
        mutableData = data.toMutableList()
        return data
    }
    suspend fun loadCatDetails(catId : String) : CatData? {
        delay(1.seconds)
        return getById(catId)
    }



}
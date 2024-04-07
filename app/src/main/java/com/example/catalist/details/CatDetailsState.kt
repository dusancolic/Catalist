package com.example.catalist.details

import com.example.catalist.api.model.CatData

data class CatDetailsState(
    val cat : CatData? = null,
    val catId : String,
    val isLoading : Boolean = false,
    val error : Throwable? = null
)
{}


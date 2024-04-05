package com.example.catalist.list

import com.example.catalist.domain.CatData

data class CatListState (
    val search: String = "",
    val cats: List<CatData> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
{

}


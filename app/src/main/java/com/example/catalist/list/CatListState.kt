package com.example.catalist.list

import com.example.catalist.api.model.CatData
import com.example.catalist.list.model.CatUiModel

data class CatListState (
    val search: String = "",
    val cats: List<CatUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
{

}


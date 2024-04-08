package com.example.catalist.list

import com.example.catalist.api.model.CatData
import com.example.catalist.list.model.CatUiModel

data class CatListState (
    val search: String = "",
    val cats: List<CatUiModel> = emptyList(),
    val filteredCats: List<CatUiModel> = emptyList(),
    val isSearching : Boolean = false,
    val query : String = "",
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
{

}


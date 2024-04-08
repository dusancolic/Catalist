package com.example.catalist.list

sealed class CatUiEvent {
    data class Search(val query: String) : CatUiEvent()
    data object ClearSearch : CatUiEvent()
    data object CloseSearch : CatUiEvent()


}
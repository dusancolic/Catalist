package com.example.catalist.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class CatDetailsViewModel constructor(
    private val repository: Repository = Repository,
    private val catId: String
) : ViewModel() {

    private val cat = Repository.getById(catId) ?: throw IllegalArgumentException("Cat not found")
    private val state = MutableStateFlow(CatDetailsState(catId = catId))
    val state1 = state.asStateFlow()
    private fun setState(reducer: CatDetailsState.() -> CatDetailsState) = state.getAndUpdate(reducer)

    init {
        loadCatDetails()
    }

    private fun loadCatDetails() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            try {
                withContext(Dispatchers.IO) {
                    val catDetails = repository.loadCatDetails(catId)
                    setState { copy(cat = catDetails) }
                }
            }
            catch (error: IOException) {
                setState { copy(error = error) }
            }
            finally {
                setState { copy(isLoading = false)}
            }
        }
    }
}
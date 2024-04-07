package com.example.catalist.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist.api.model.CatData
import com.example.catalist.repository.Repository
import com.example.catalist.list.model.CatUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class CatListViewModel constructor(
    private val repository: Repository = Repository

) : ViewModel(){
    private val state = MutableStateFlow(CatListState())
    val state1 = state.asStateFlow()
    private fun setState(reducer: CatListState.() -> CatListState) = state.getAndUpdate(reducer)

    init{
        loadCats()
    }

    private fun loadCats()
    {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            try{
                withContext(Dispatchers.IO){
                   val cats = repository.loadCats().map { it.asCatUiModel() }
                    setState { copy(cats = cats)}
                }

            }
            catch (error: Exception){
                setState { copy(error = error)}
            }
            finally{
                setState { copy(isLoading = false) }
            }
        }
    }
    private fun CatData.asCatUiModel() = CatUiModel(
        id = this.id,
        name = this.name,
        alt_names = this.alt_names,
        description = this.description,
        temperament = this.temperament,
    )

}
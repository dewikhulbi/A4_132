package com.example.finalpam.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.repository.ManajerRepository
import com.example.finalpam.ui.view.manajer.DestinasiEditManajer
import com.example.finalpam.ui.viewmodel.manajer.InsertManajerUiEvent
import com.example.finalpam.ui.viewmodel.manajer.InsertManajerUiState
import com.example.finalpam.ui.viewmodel.manajer.toMnjr
import com.example.finalpam.ui.viewmodel.manajer.toUiStateMnjr
import kotlinx.coroutines.launch

class EditManajerViewModel(
    savedStateHandle: SavedStateHandle,
    private val manajerRepository: ManajerRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertManajerUiState())
        private set

    val id_manajer: String = checkNotNull(savedStateHandle[DestinasiEditManajer.id_manajer])

    init {
        viewModelScope.launch {
            uiState = manajerRepository.getManajerById(id_manajer).toUiStateMnjr()
        }
    }

    fun updateInsertMnjrState(insertManajerUiEvent: InsertManajerUiEvent) {
        uiState = InsertManajerUiState(insertManajerUiEvent = insertManajerUiEvent)
    }

    suspend fun editManajer(){
        viewModelScope.launch {
            try {
                manajerRepository.updateManajer(id_manajer, uiState.insertManajerUiEvent.toMnjr())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
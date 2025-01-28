package com.example.finalpam.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.repository.PemilikRepository
import com.example.finalpam.ui.view.pemilik.DestinasiEditPemilik
import com.example.finalpam.ui.viewmodel.pemilik.InsertPemilikUiEvent
import com.example.finalpam.ui.viewmodel.pemilik.InsertPemilikUiState
import com.example.finalpam.ui.viewmodel.pemilik.toPmlk
import com.example.finalpam.ui.viewmodel.pemilik.toUiStatePmlk
import kotlinx.coroutines.launch

class EditPemilikViewModel(
    savedStateHandle: SavedStateHandle,
    private val pemilikRepository: PemilikRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertPemilikUiState())
        private set

    val id_pemilik: String = checkNotNull(savedStateHandle[DestinasiEditPemilik.id_pemilik])

    init {
        viewModelScope.launch {
            uiState = pemilikRepository.getPemilikById(id_pemilik).toUiStatePmlk()
        }
    }

    fun updateInsertPmlkState(insertPemilikUiEvent: InsertPemilikUiEvent) {
        uiState = InsertPemilikUiState(insertPemilikUiEvent = insertPemilikUiEvent)
    }

    suspend fun editPemilik(){
        viewModelScope.launch {
            try {
                pemilikRepository.updatePemilik(id_pemilik, uiState.insertPemilikUiEvent.toPmlk())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
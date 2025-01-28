package com.example.finalpam.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.repository.PropertiRepository
import com.example.finalpam.ui.view.properti.DestinasiEditProperti
import com.example.finalpam.ui.viewmodel.properti.InsertPropertiUiEvent
import com.example.finalpam.ui.viewmodel.properti.InsertPropertiUiState
import com.example.finalpam.ui.viewmodel.properti.toPrpti
import com.example.finalpam.ui.viewmodel.properti.toUiStatePrpti
import kotlinx.coroutines.launch

class EditPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val propertiRepository: PropertiRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertPropertiUiState())
        private set

    val id_properti: String = checkNotNull(savedStateHandle[DestinasiEditProperti.id_properti])

    init {
        viewModelScope.launch {
            uiState = propertiRepository.getPropertiById(id_properti).toUiStatePrpti()
        }
    }

    fun updateInsertPrptiState(insertPropertiUiEvent: InsertPropertiUiEvent) {
        uiState = InsertPropertiUiState(insertPropertiUiEvent = insertPropertiUiEvent)
    }

    suspend fun editProperti(){
        viewModelScope.launch {
            try {
                propertiRepository.updateProperti(id_properti, uiState.insertPropertiUiEvent.toPrpti())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
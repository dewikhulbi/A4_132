package com.example.finalpam.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.repository.JenisRepository
import com.example.finalpam.repository.PropertiRepository
import com.example.finalpam.ui.view.jenis.DestinasiEditJenis
import kotlinx.coroutines.launch

class EditJenisViewModel(
    savedStateHandle: SavedStateHandle,
    private val jenisRepository: JenisRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertJenisUiState())
        private set

    val id_jenis: String = checkNotNull(savedStateHandle[DestinasiEditJenis.id_jenis])

    init {
        viewModelScope.launch {
            uiState = jenisRepository.getJenisById(id_jenis).toUiStateJns()
        }
    }

    fun updateInsertJnsState(insertJenisUiEvent: InsertJenisUiEvent) {
        uiState = InsertJenisUiState(insertJenisUiEvent = insertJenisUiEvent)
    }

    suspend fun editJenis(){
        viewModelScope.launch {
            try {
                jenisRepository.updateJenis(id_jenis, uiState.insertJenisUiEvent.toJns())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
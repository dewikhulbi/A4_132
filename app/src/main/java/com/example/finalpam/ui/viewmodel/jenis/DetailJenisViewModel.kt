package com.example.finalpam.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Jenis
import com.example.finalpam.repository.JenisRepository
import com.example.finalpam.repository.PropertiRepository
import com.example.finalpam.ui.view.jenis.DestinasiDetailJenis
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailJnsUiState{
    data class Success(val jenis: Jenis) : DetailJnsUiState()
    object Error : DetailJnsUiState()
    object Loading : DetailJnsUiState()
}

class DetailJenisViewModel(
    savedStateHandle: SavedStateHandle,
    private val jenisRepository: JenisRepository
) : ViewModel() {

    private val id_jenis: String = checkNotNull(savedStateHandle[DestinasiDetailJenis.id_jenis])
    var detailJnsUiState: DetailJnsUiState by mutableStateOf(DetailJnsUiState.Loading)
        private set

    init {
        getJnsById()
    }

    fun getJnsById(){
        viewModelScope.launch {
            detailJnsUiState = DetailJnsUiState.Loading
            detailJnsUiState = try {
                DetailJnsUiState.Success(jenis = jenisRepository.getJenisById(id_jenis))
            } catch (e: IOException) {
                DetailJnsUiState.Error
            }
        }
    }
}
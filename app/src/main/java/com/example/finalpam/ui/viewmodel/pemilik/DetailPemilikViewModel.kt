package com.example.finalpam.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Pemilik
import com.example.finalpam.repository.PemilikRepository
import com.example.finalpam.ui.view.pemilik.DestinasiDetailPemilik
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailPmlkUiState{
    data class Success(val pemilik: Pemilik) : DetailPmlkUiState()
    object Error : DetailPmlkUiState()
    object Loading : DetailPmlkUiState()
}

class DetailPemilikViewModel(
    savedStateHandle: SavedStateHandle,
    private val pemilikRepository: PemilikRepository
) : ViewModel() {

    private val id_pemilik: String = checkNotNull(savedStateHandle[DestinasiDetailPemilik.id_pemilik])
    var detailPmlkUiState: DetailPmlkUiState by mutableStateOf(DetailPmlkUiState.Loading)
        private set

    init {
        getPmlkById()
    }

    fun getPmlkById(){
        viewModelScope.launch {
            detailPmlkUiState = DetailPmlkUiState.Loading
            detailPmlkUiState = try {
                DetailPmlkUiState.Success(pemilik = pemilikRepository.getPemilikById(id_pemilik))
            } catch (e: IOException) {
                DetailPmlkUiState.Error
            }
        }
    }
}
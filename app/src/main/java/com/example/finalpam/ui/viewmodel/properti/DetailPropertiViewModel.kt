package com.example.finalpam.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Properti
import com.example.finalpam.repository.PropertiRepository
import com.example.finalpam.ui.view.properti.DestinasiDetailProperti
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailPrptiUiState{
    data class Success(val properti: Properti) : DetailPrptiUiState()
    object Error : DetailPrptiUiState()
    object Loading : DetailPrptiUiState()
}

class DetailPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val propertiRepository: PropertiRepository
) : ViewModel() {

    private val id_properti: String = checkNotNull(savedStateHandle[DestinasiDetailProperti.id_properti])
    var detailPrptiUiState: DetailPrptiUiState by mutableStateOf(DetailPrptiUiState.Loading)
        private set

    init {
        getPrptiById()
    }

    fun getPrptiById(){
        viewModelScope.launch {
            detailPrptiUiState = DetailPrptiUiState.Loading
            detailPrptiUiState = try {
                DetailPrptiUiState.Success(properti = propertiRepository.getPropertiById(id_properti))
            } catch (e: IOException) {
                DetailPrptiUiState.Error
            }
        }
    }
}
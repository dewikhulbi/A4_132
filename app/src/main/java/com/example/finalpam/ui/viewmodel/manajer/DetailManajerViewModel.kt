package com.example.finalpam.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Manajer
import com.example.finalpam.repository.ManajerRepository
import com.example.finalpam.ui.view.manajer.DestinasiDetailManajer
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailMnjrUiState{
    data class Success(val manajer: Manajer) : DetailMnjrUiState()
    object Error : DetailMnjrUiState()
    object Loading : DetailMnjrUiState()
}

class DetailManajerViewModel(
    savedStateHandle: SavedStateHandle,
    private val manajerRepository: ManajerRepository
) : ViewModel() {

    private val id_manajer: String = checkNotNull(savedStateHandle[DestinasiDetailManajer.id_manajer])
    var detailMnjrUiState: DetailMnjrUiState by mutableStateOf(DetailMnjrUiState.Loading)
        private set

    init {
        getMnjrById()
    }

    fun getMnjrById(){
        viewModelScope.launch {
            detailMnjrUiState = DetailMnjrUiState.Loading
            detailMnjrUiState = try {
                DetailMnjrUiState.Success(manajer = manajerRepository.getManajerById(id_manajer))
            } catch (e: IOException) {
                DetailMnjrUiState.Error
            }
        }
    }
}
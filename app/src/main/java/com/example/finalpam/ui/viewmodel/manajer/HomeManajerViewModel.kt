package com.example.finalpam.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalpam.model.Manajer
import com.example.finalpam.repository.ManajerRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class  HomeManajerUiState  {
    data class  Success ( val  manajer :  List <Manajer> )  :  HomeManajerUiState ()
    object  Error  :  HomeManajerUiState ()
    object  Loading  :  HomeManajerUiState ()
}

class  HomeManajerViewModel(private val mnjr: ManajerRepository)  :  ViewModel() {
    var  mnjrUIState :  HomeManajerUiState  by  mutableStateOf ( HomeManajerUiState . Loading )
    var selectedManajer : String by mutableStateOf("")
        private set

    init  {
        getMnjr ()
    }

    fun  getMnjr () {
        viewModelScope . launch  {
            mnjrUIState  =  HomeManajerUiState . Loading
            mnjrUIState  = try  {
                HomeManajerUiState . Success (mnjr. getManajer ())
            }  catch  (e : IOException) {
                HomeManajerUiState . Error
            }  catch  (e : HttpException) {
                HomeManajerUiState . Error
            }
        }
    }


    fun  deleteMnjr ( id_manajer :  String ) {
        viewModelScope . launch  {
            try  {
                mnjr. deleteManajer ( id_manajer )
            }  catch  (e : IOException){
                HomeManajerUiState . Error
            }  catch  (e : HttpException){
                HomeManajerUiState . Error
            }
        }
    }

    fun selectManajer(id_manajer: String) {
        selectedManajer = id_manajer
    }
}
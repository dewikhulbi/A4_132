package com.example.finalpam.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalpam.model.Pemilik
import com.example.finalpam.repository.PemilikRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class  HomePemilikUiState  {
    data class  Success ( val  pemilik :  List <Pemilik> )  :  HomePemilikUiState ()
    object  Error  :  HomePemilikUiState ()
    object  Loading  :  HomePemilikUiState ()
}

class  HomePemilikViewModel(private val pmlk: PemilikRepository)  :  ViewModel() {
    var  pmlkUIState :  HomePemilikUiState  by  mutableStateOf ( HomePemilikUiState . Loading )
    var selectedPemilik : String by mutableStateOf("")
        private set

    init  {
        getPmlk ()
    }

    fun  getPmlk () {
        viewModelScope . launch  {
            pmlkUIState  =  HomePemilikUiState . Loading
            pmlkUIState  = try  {
                HomePemilikUiState . Success (pmlk. getPemilik ())
            }  catch  (e : IOException) {
                HomePemilikUiState . Error
            }  catch  (e : HttpException) {
                HomePemilikUiState . Error
            }
        }
    }


    fun  deletePmlk ( id_pemilik :  String ) {
        viewModelScope . launch  {
            try  {
                pmlk. deletePemilik ( id_pemilik )
            }  catch  (e : IOException){
                HomePemilikUiState . Error
            }  catch  (e : HttpException){
                HomePemilikUiState . Error
            }
        }
    }

    fun selectPemilik(id_pemilik: String) {
        selectedPemilik = id_pemilik
    }
}
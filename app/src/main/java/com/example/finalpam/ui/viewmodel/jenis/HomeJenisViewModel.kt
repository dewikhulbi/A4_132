package com.example.finalpam.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalpam.model.Jenis
import com.example.finalpam.repository.JenisRepository
import com.example.finalpam.repository.PropertiRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class  HomeJenisUiState  {
    data class  Success ( val  jenis :  List <Jenis> )  :  HomeJenisUiState ()
    object  Error  :  HomeJenisUiState ()
    object  Loading  :  HomeJenisUiState ()
}

class  HomeJenisViewModel(private val jns: JenisRepository)  :  ViewModel() {
    var  jnsUIState :  HomeJenisUiState  by  mutableStateOf ( HomeJenisUiState . Loading )
    var selectedJenis : String by mutableStateOf("")
        private set

    init  {
        getJns ()
    }

    fun  getJns () {
        viewModelScope . launch  {
            jnsUIState  =  HomeJenisUiState . Loading
            jnsUIState  = try  {
                HomeJenisUiState . Success (jns. getJenis ())
            }  catch  (e : IOException) {
                HomeJenisUiState . Error
            }  catch  (e : HttpException) {
                HomeJenisUiState . Error
            }
        }
    }


    fun  deleteJns ( id_jenis :  String ) {
        viewModelScope . launch  {
            try  {
                jns. deleteJenis ( id_jenis )
            }  catch  (e : IOException){
                HomeJenisUiState . Error
            }  catch  (e : HttpException){
                HomeJenisUiState . Error
            }
        }
    }

    fun selectJenis(id_jenis: String) {
        selectedJenis = id_jenis
    }
}
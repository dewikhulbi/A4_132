package com.example.finalpam.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Pemilik
import com.example.finalpam.repository.PemilikRepository
import kotlinx.coroutines.launch

class  InsertPemilikViewModel(private val pmlk: PemilikRepository)  :  ViewModel() {
    var  uiState  by  mutableStateOf ( InsertPemilikUiState ())
        private set

    fun  updateInsertPmlkState ( insertPemilikUiEvent :  InsertPemilikUiEvent ) {
        uiState  =  InsertPemilikUiState ( insertPemilikUiEvent =  insertPemilikUiEvent )
    }

    suspend fun  insertPmlk () {
        viewModelScope . launch  {
            try  {
                pmlk. insertPemilik (uiState.insertPemilikUiEvent. toPmlk ())
            } catch  (e : Exception ){
                e. printStackTrace ()
            }
        }
    }
}


data class  InsertPemilikUiState (
    val  insertPemilikUiEvent :  InsertPemilikUiEvent  =  InsertPemilikUiEvent ()
)

data class   InsertPemilikUiEvent (
    val  id_pemilik :  String  =  "" ,
    val  nama_pemilik :  String  =  "" ,
    val  kontak_pemilik :  String  =  "" ,
)

fun  InsertPemilikUiEvent . toPmlk () : Pemilik =  Pemilik (
    id_pemilik =  id_pemilik,
    nama_pemilik =  nama_pemilik,
    kontak_pemilik =  kontak_pemilik
)

fun Pemilik. toUiStatePmlk () :  InsertPemilikUiState  =  InsertPemilikUiState (
    insertPemilikUiEvent =  toInsertPemilikUiEvent ()
)

fun Pemilik. toInsertPemilikUiEvent () :  InsertPemilikUiEvent  =  InsertPemilikUiEvent (
    id_pemilik =  id_pemilik,
    nama_pemilik =  nama_pemilik,
    kontak_pemilik =  kontak_pemilik
)
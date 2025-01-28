package com.example.finalpam.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Jenis
import com.example.finalpam.repository.JenisRepository
import com.example.finalpam.repository.PropertiRepository
import kotlinx.coroutines.launch

class  InsertJenisViewModel(private val jns: JenisRepository)  :  ViewModel() {
    var  uiState  by  mutableStateOf ( InsertJenisUiState ())
        private set

    fun  updateInsertJnsState ( insertJenisUiEvent :  InsertJenisUiEvent ) {
        uiState  =  InsertJenisUiState ( insertJenisUiEvent =  insertJenisUiEvent )
    }

    suspend fun  insertJns () {
        viewModelScope . launch  {
            try  {
                jns. insertJenis (uiState.insertJenisUiEvent. toJns ())
            } catch  (e : Exception ){
                e. printStackTrace ()
            }
        }
    }
}


data class  InsertJenisUiState (
    val listJenis: List<Jenis> = listOf(), // Daftar dosen
    val  insertJenisUiEvent :  InsertJenisUiEvent  =  InsertJenisUiEvent ()
)

data class  InsertJenisUiEvent (
    val  id_jenis :  String  =  "" ,
    val  nama_jenis :  String  =  "" ,
    val  deskripsi_jenis :  String  =  "" ,
)

fun  InsertJenisUiEvent . toJns () : Jenis =  Jenis (
    id_jenis =  id_jenis,
    nama_jenis =  nama_jenis,
    deskripsi_jenis =  deskripsi_jenis
)

fun  Jenis . toUiStateJns () :  InsertJenisUiState  =  InsertJenisUiState (
    insertJenisUiEvent =  toInsertJenisUiEvent ()
)

fun  Jenis . toInsertJenisUiEvent () :  InsertJenisUiEvent  =  InsertJenisUiEvent (
    id_jenis =  id_jenis,
    nama_jenis =  nama_jenis,
    deskripsi_jenis =  deskripsi_jenis
)
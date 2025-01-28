package com.example.finalpam.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Properti
import com.example.finalpam.repository.PropertiRepository
import kotlinx.coroutines.launch

class  InsertPropertiViewModel(private val prpti: PropertiRepository)  :  ViewModel() {
    var  uiState  by  mutableStateOf ( InsertPropertiUiState ())
        private set

    fun  updateInsertPrptiState ( insertPropertiUiEvent :  InsertPropertiUiEvent ) {
        uiState  =  InsertPropertiUiState ( insertPropertiUiEvent =  insertPropertiUiEvent )
    }

    suspend fun  insertPrpti () {
        viewModelScope . launch  {
            try  {
                prpti. insertProperti (uiState.insertPropertiUiEvent. toPrpti ())
            } catch  (e : Exception ){
                e. printStackTrace ()
            }
        }
    }
}


data class  InsertPropertiUiState (
    val  insertPropertiUiEvent :  InsertPropertiUiEvent  =  InsertPropertiUiEvent ()
)

data class  InsertPropertiUiEvent (
    val  id_properti :  String  =  "" ,
    val  nama_properti :  String  =  "" ,
    val  deskripsi_properti :  String  =  "" ,
    val  lokasi :  String  =  "" ,
    val  harga :  String  =  "" ,
    val  status_properti :  String  =  "" ,
    val  id_jenis :  String  =  "" ,
    val  id_pemilik :  String  =  "" ,
    val  id_manajer :  String  =  "" ,
)

fun  InsertPropertiUiEvent . toPrpti () : Properti =  Properti (
    id_properti =  id_properti,
    nama_properti =  nama_properti,
    deskripsi_properti =  deskripsi_properti,
    lokasi =  lokasi,
    harga =  harga,
    status_properti =  status_properti,
    id_jenis =  id_jenis,
    id_pemilik =  id_pemilik,
    id_manajer =  id_manajer
)

fun Properti. toUiStatePrpti () :  InsertPropertiUiState  =  InsertPropertiUiState (
    insertPropertiUiEvent =  toInsertPropertiUiEvent ()
)

fun Properti. toInsertPropertiUiEvent () :  InsertPropertiUiEvent  =  InsertPropertiUiEvent (
    id_properti =  id_properti,
    nama_properti =  nama_properti,
    deskripsi_properti =  deskripsi_properti,
    lokasi =  lokasi,
    harga =  harga,
    status_properti =  status_properti,
    id_jenis =  id_jenis,
    id_pemilik =  id_pemilik,
    id_manajer =  id_manajer

)
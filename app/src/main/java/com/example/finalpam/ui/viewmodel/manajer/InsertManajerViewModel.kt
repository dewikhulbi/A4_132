package com.example.finalpam.ui.viewmodel.manajer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Manajer
import com.example.finalpam.repository.ManajerRepository
import com.example.finalpam.repository.PropertiRepository
import kotlinx.coroutines.launch

class  InsertManajerViewModel(private val mnjr: ManajerRepository)  :  ViewModel() {
    var  uiState  by  mutableStateOf ( InsertManajerUiState ())
        private set

    fun  updateInsertMnjrState ( insertManajerUiEvent :  InsertManajerUiEvent ) {
        uiState  =  InsertManajerUiState ( insertManajerUiEvent =  insertManajerUiEvent )
    }

    suspend fun  insertMnjr () {
        viewModelScope . launch  {
            try  {
                mnjr. insertManajer (uiState.insertManajerUiEvent. toMnjr ())
            } catch  (e : Exception ){
                e. printStackTrace ()
            }
        }
    }
}


data class  InsertManajerUiState (
    val  insertManajerUiEvent :  InsertManajerUiEvent  =  InsertManajerUiEvent ()
)

data class  InsertManajerUiEvent (
    val  id_manajer :  String  =  "" ,
    val  nama_manajer :  String  =  "" ,
    val  kontak_manajer :  String  =  "" ,
)

fun  InsertManajerUiEvent . toMnjr () : Manajer =  Manajer (
    id_manajer =  id_manajer,
    nama_manajer =  nama_manajer,
    kontak_manajer =  kontak_manajer
)

fun  Manajer . toUiStateMnjr () :  InsertManajerUiState  =  InsertManajerUiState (
    insertManajerUiEvent =  toInsertManajerUiEvent ()
)

fun  Manajer . toInsertManajerUiEvent () :  InsertManajerUiEvent  =  InsertManajerUiEvent (
    id_manajer =  id_manajer,
    nama_manajer =  nama_manajer,
    kontak_manajer =  kontak_manajer
)
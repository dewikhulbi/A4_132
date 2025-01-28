package com.example.finalpam.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalpam.model.Properti
import com.example.finalpam.repository.PropertiRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class  HomePropertiUiState  {
    data class  Success ( val  properti :  List <Properti> )  :  HomePropertiUiState ()
    object  Error  :  HomePropertiUiState ()
    object  Loading  :  HomePropertiUiState ()
}

class  HomePropertiViewModel(
    private val prpti: PropertiRepository
)  :  ViewModel() {
    var  prptiUIState :  HomePropertiUiState  by  mutableStateOf ( HomePropertiUiState . Loading )
        private set

    init  {
        getPrpti ()
    }

    fun  getPrpti () {
        viewModelScope . launch  {
            prptiUIState  =  HomePropertiUiState . Loading
            prptiUIState  = try  {
                HomePropertiUiState . Success (prpti. getProperti ())
            }  catch  (e : IOException) {
                HomePropertiUiState . Error
            }  catch  (e : HttpException) {
                HomePropertiUiState . Error
            }
        }
    }


    fun  deletePrpti ( id_properti :  String ) {
        viewModelScope . launch  {
            try  {
                prpti. deleteProperti ( id_properti )
            }  catch  (e : IOException){
                HomePropertiUiState . Error
            }  catch  (e : HttpException){
                HomePropertiUiState . Error
            }
        }
    }
}
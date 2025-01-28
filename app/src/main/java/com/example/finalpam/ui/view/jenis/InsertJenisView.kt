package com.example.finalpam.ui.view.jenis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.jenis.InsertJenisUiEvent
import com.example.finalpam.ui.viewmodel.jenis.InsertJenisUiState
import com.example.finalpam.ui.viewmodel.jenis.InsertJenisViewModel
import kotlinx.coroutines.launch

object DestinasiEntryJenis: DestinasiNavigasi {
    override val route="entryjenis"
    override val titleRes="Entry Jenis"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun  EntryJnsScreen (
    navigateBack :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    viewModel : InsertJenisViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val  coroutineScope  =  rememberCoroutineScope ()

    val  scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiEntryJenis .titleRes,
                canNavigateBack =  true ,
                scrollBehavior =  scrollBehavior ,
                navigateUp =  navigateBack
            )
        }
    )  {  innerPadding  ->
        EntryBody (
            insertUiState =  viewModel .uiState,
            onJenisValueChange =  viewModel :: updateInsertJnsState,
            onSaveClick =  {
                coroutineScope . launch  {
                    viewModel . insertJns ()
                    navigateBack ()
                }
            },
            modifier =  Modifier
                . padding ( innerPadding )
                . verticalScroll ( rememberScrollState ())
                . fillMaxWidth ()
        )

    }
}

@Composable
fun  EntryBody (
    insertUiState : InsertJenisUiState,

    onJenisValueChange :  (InsertJenisUiEvent)  ->  Unit ,
    onSaveClick :  ()  ->  Unit ,
    modifier : Modifier =  Modifier
) {
    Column (
        verticalArrangement =  Arrangement . spacedBy ( 18 . dp ),
        modifier =  modifier . padding ( 12 . dp )
    )  {
        FormInput (
            insertUiEvent =  insertUiState .insertJenisUiEvent,
            onValueChange =  onJenisValueChange ,
            modifier =  Modifier . fillMaxWidth ()
        )
        Button (
            onClick =  onSaveClick ,
            shape =  MaterialTheme . shapes .small,
            modifier =  Modifier . fillMaxWidth ()
        )  {
            Text ( text =  "Simpan" )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  FormInput (
    insertUiEvent :  InsertJenisUiEvent,
    modifier : Modifier =  Modifier,
    onValueChange :  ( InsertJenisUiEvent )  ->  Unit  =  {},
    enabled :  Boolean  = true
) {
    Column (
        modifier =  modifier ,

        verticalArrangement =  Arrangement . spacedBy ( 12 . dp )
    )  {
        OutlinedTextField (
            value =  insertUiEvent .id_jenis,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( id_jenis =  it ))  } ,
            label =  {  Text ( "id_jenis" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertUiEvent .nama_jenis,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( nama_jenis =  it ))  } ,
            label =  {  Text ( "nama_jenis" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertUiEvent .deskripsi_jenis,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( deskripsi_jenis =  it ))  } ,
            label =  {  Text ( "deskripsi_jenis" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        if  ( enabled ) {
            Text (
                text =  "Isi Semua Data!" ,
                modifier =  Modifier . padding ( 12 . dp )
            )
        }
        Divider (
            thickness =  8 . dp ,
            modifier =  Modifier . padding ( 12 . dp )
        )
    }
}
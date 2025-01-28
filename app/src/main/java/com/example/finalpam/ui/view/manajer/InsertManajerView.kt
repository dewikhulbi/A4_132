package com.example.finalpam.ui.view.manajer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.view.manajer.DestinasiEntryManajer
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.manajer.InsertManajerUiEvent
import com.example.finalpam.ui.viewmodel.manajer.InsertManajerUiState
import com.example.finalpam.ui.viewmodel.manajer.InsertManajerViewModel
import kotlinx.coroutines.launch


object DestinasiEntryManajer: DestinasiNavigasi {
    override val route="entrymanajer"
    override val titleRes="Entry Manajer"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun  EntryMnjrScreen (
    navigateBack :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    viewModel : InsertManajerViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val  coroutineScope  =  rememberCoroutineScope ()

    val  scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiEntryManajer .titleRes,
                canNavigateBack =  true ,
                scrollBehavior =  scrollBehavior ,
                navigateUp =  navigateBack
            )
        }
    )  {  innerPadding  ->
        EntryBody (
            insertUiState =  viewModel .uiState,
            onManajerValueChange =  viewModel :: updateInsertMnjrState,
            onSaveClick =  {
                coroutineScope . launch  {
                    viewModel . insertMnjr ()
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
    insertUiState : InsertManajerUiState,

    onManajerValueChange :  (InsertManajerUiEvent)  ->  Unit,
    onSaveClick :  ()  ->  Unit,
    modifier : Modifier =  Modifier
) {
    Column (
        verticalArrangement =  Arrangement . spacedBy ( 18 . dp ),
        modifier =  modifier . padding ( 12 . dp )
    )  {
        FormInput (
            insertUiEvent =  insertUiState .insertManajerUiEvent,
            onValueChange =  onManajerValueChange ,
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
    insertUiEvent : InsertManajerUiEvent,
    modifier : Modifier =  Modifier,
    onValueChange :  (InsertManajerUiEvent)  ->  Unit  =  {},
    enabled :  Boolean  = true
) {
    Column (
        modifier =  modifier ,

        verticalArrangement =  Arrangement . spacedBy ( 12 . dp )
    )  {
        OutlinedTextField (
            value =  insertUiEvent .id_manajer,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( id_manajer =  it ))  } ,
            label =  {  Text ( "id_manajer" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertUiEvent .nama_manajer,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( nama_manajer =  it ))  } ,
            label =  {  Text ( "nama_manajer" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertUiEvent .kontak_manajer,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( kontak_manajer =  it ))  } ,
            label =  {  Text ( "kontak_manajer" )  } ,
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
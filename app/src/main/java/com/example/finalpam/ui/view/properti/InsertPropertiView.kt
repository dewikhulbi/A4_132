package com.example.finalpam.ui.view.properti

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.jenis.HomeJenisUiState
import com.example.finalpam.ui.viewmodel.jenis.HomeJenisViewModel
import com.example.finalpam.ui.viewmodel.manajer.HomeManajerUiState
import com.example.finalpam.ui.viewmodel.manajer.HomeManajerViewModel
import com.example.finalpam.ui.viewmodel.pemilik.HomePemilikUiState
import com.example.finalpam.ui.viewmodel.pemilik.HomePemilikViewModel
import com.example.finalpam.ui.viewmodel.properti.InsertPropertiUiEvent
import com.example.finalpam.ui.viewmodel.properti.InsertPropertiUiState
import com.example.finalpam.ui.viewmodel.properti.InsertPropertiViewModel
import kotlinx.coroutines.launch

object DestinasiEntryProperti: DestinasiNavigasi {
    override val route="entryproperti"
    override val titleRes="Entry Properti"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun  EntryPrptiScreen (
    navigateBack :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    viewModel : InsertPropertiViewModel =  viewModel ( factory =  PenyediaViewModel .Factory),
    pemiliikViewModel : HomePemilikViewModel = viewModel ( factory = PenyediaViewModel.Factory),
    manajerViewModel: HomeManajerViewModel = viewModel(factory = PenyediaViewModel.Factory),
    jenisViewModel: HomeJenisViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val  coroutineScope  =  rememberCoroutineScope ()

    val  scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()

    val pmlkUIState = pemiliikViewModel.pmlkUIState
    val selectedPemilik by remember { mutableStateOf(pemiliikViewModel.selectedPemilik) }

    val mnjrUIState = manajerViewModel.mnjrUIState
    val selectedManajer by remember { mutableStateOf(manajerViewModel.selectedManajer) }

    val jnsUIState = jenisViewModel.jnsUIState
    val selectedJenis by remember { mutableStateOf(jenisViewModel.selectedJenis) }

    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiEntryProperti .titleRes,
                canNavigateBack =  true ,
                scrollBehavior =  scrollBehavior ,
                navigateUp =  navigateBack
            )
        }
    )  {  innerPadding  ->
        EntryBody (
            insertUiState =  viewModel .uiState,
            onPropertiValueChange =  viewModel :: updateInsertPrptiState,
            onSaveClick =  {
                coroutineScope . launch  {
                    viewModel . insertPrpti ()
                    navigateBack ()
                }
            },
            pmlkUIState = pmlkUIState,
            selectedPemilik = selectedPemilik,
            selectedManajer = selectedManajer,
            selectedJenis = selectedJenis,
            onPemilikSelected = {pemiliikViewModel.selectPemilik(it)},
            onManajerSelected = {manajerViewModel.selectManajer(it)},
            onJenisSelected = {jenisViewModel.selectJenis(it)},
            mnjrUIState = mnjrUIState,
            jnsUIState = jnsUIState,
            modifier =  Modifier
                . padding ( innerPadding )
                .padding(top = 1.dp)
                . verticalScroll ( rememberScrollState ())
                . fillMaxWidth ()
        )

    }
}

@Composable
fun  EntryBody (
    insertUiState : InsertPropertiUiState,
    onPropertiValueChange :  (InsertPropertiUiEvent)  ->  Unit,
    pmlkUIState : HomePemilikUiState,
    mnjrUIState : HomeManajerUiState,
    jnsUIState : HomeJenisUiState,
    selectedPemilik : String,
    selectedManajer : String,
    selectedJenis : String,
    onPemilikSelected: (String) -> Unit ={},
    onManajerSelected: (String) -> Unit = {},
    onJenisSelected: (String) -> Unit = {},
    onSaveClick :  ()  ->  Unit,
    modifier : Modifier =  Modifier
) {
    Column (
        verticalArrangement =  Arrangement . spacedBy ( 1 . dp ),
        modifier =  modifier . padding ( 1 . dp )
    )  {
        FormInput (
            insertUiEvent =  insertUiState .insertPropertiUiEvent,
            onValueChange =  onPropertiValueChange ,
            modifier =  Modifier . fillMaxWidth (),
            pmlkUIState = pmlkUIState,
            selectedPemilik = selectedPemilik,
            onPemilikSelected = onPemilikSelected,
            mnjrUIState = mnjrUIState,
            selectedManajer = selectedManajer,
            onManajerSelected = onManajerSelected,
            jnsUIState = jnsUIState,
            selectedJenis = selectedJenis,
            onJenisSelected = onJenisSelected
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
    insertUiEvent : InsertPropertiUiEvent,
    modifier : Modifier =  Modifier,
    pmlkUIState : HomePemilikUiState,
    mnjrUIState : HomeManajerUiState,
    jnsUIState : HomeJenisUiState,
    selectedPemilik: String,
    selectedManajer: String,
    selectedJenis : String,
    onPemilikSelected: (String) -> Unit={},
    onManajerSelected: (String) -> Unit={},
    onJenisSelected: (String) -> Unit={},

    onValueChange :  (InsertPropertiUiEvent)  ->  Unit  =  {},
    enabled :  Boolean  = true
) {
    val status_properti = listOf("Tersedia", "Tersewa", "Dijual")
    Column (
        modifier =  modifier ,
        verticalArrangement =  Arrangement . spacedBy ( 1 . dp )
    )  {
        OutlinedTextField (
            value =  insertUiEvent .id_properti,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( id_properti =  it ))  } ,
            label =  {  Text ( "id_properti" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertUiEvent .nama_properti,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( nama_properti =  it ))  } ,
            label =  {  Text ( "nama_properti" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertUiEvent .deskripsi_properti,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( deskripsi_properti =  it ))  } ,
            label =  {  Text ( "kontak_properti" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertUiEvent .lokasi,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( lokasi =  it ))  } ,
            label =  {  Text ( "lokasi" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertUiEvent .harga,
            onValueChange =  {  onValueChange ( insertUiEvent . copy ( harga =  it ))  } ,
            label =  {  Text ( "harga" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        Text(text = "Status Properti")
        Row (modifier = Modifier.fillMaxWidth()
        ){
            status_properti.forEach{status ->
                Row  (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = insertUiEvent.status_properti == status,
                        onClick = {
                            onValueChange(insertUiEvent.copy(status_properti = status))
                        },
                    )
                    Text(
                        text = status,
                    )
                }
            }
        }
        when(jnsUIState) {
            is HomeJenisUiState.Loading -> {
                Text(text = "Loading")
            }
            is HomeJenisUiState.Error -> {
                Text(text = "Error")
            }
            is HomeJenisUiState.Success -> {
                DropDownMenu(
                    title = "ID Jenis",
                    options = jnsUIState.jenis.map { it.id_jenis},
                    selectedOption = selectedJenis,
                    onOptionSelected = { id_jenis ->
                        onJenisSelected(id_jenis)
                        onValueChange(insertUiEvent.copy(id_jenis = id_jenis))
                    }
                )
            }
        }
        when(pmlkUIState) {
            is HomePemilikUiState.Loading -> {
                Text(text = "Loading")
            }
            is HomePemilikUiState.Error -> {
                Text(text = "Error")
            }
            is HomePemilikUiState.Success -> {
                DropDownMenu(
                    title = "ID Pemilik",
                    options = pmlkUIState.pemilik.map { it.id_pemilik},
                    selectedOption = selectedPemilik,
                    onOptionSelected = { id_pemilik ->
                        onPemilikSelected(id_pemilik)
                        onValueChange(insertUiEvent.copy(id_pemilik = id_pemilik))
                    }
                )
            }
        }
        when(mnjrUIState) {
            is HomeManajerUiState.Loading -> {
                Text(text = "Loading")
            }
            is HomeManajerUiState.Error -> {
                Text(text = "Error")
            }
            is HomeManajerUiState.Success -> {
                DropDownMenu(
                    title = "ID Manajer",
                    options = mnjrUIState.manajer.map { it.id_manajer},
                    selectedOption = selectedManajer,
                    onOptionSelected = { id_manajer ->
                        onManajerSelected(id_manajer)
                        onValueChange(insertUiEvent.copy(id_manajer = id_manajer))
                    }
                )
            }
        }
        if  ( enabled ) {
            Text (
                text =  "Isi Semua Data!" ,
                modifier =  Modifier . padding ( 1 . dp )
            )
        }
        Divider (
            thickness =  1 . dp ,
            modifier =  Modifier . padding ( 1 . dp )
        )
    }
}

@Composable
fun DropDownMenu(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    var expanded by remember { mutableStateOf(false) }
    var currentSelected by remember { mutableStateOf(selectedOption) }

    Column {
        OutlinedTextField(
            value = currentSelected,
            onValueChange = { },
            label = { Text(title) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if(expanded) Icons . Default . ArrowDropDown else Icons . Default . ArrowDropDown,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        currentSelected = option
                        expanded = false
                    }
                )
            }
        }
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red
            )
        }
    }
}
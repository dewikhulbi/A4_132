package com.example.finalpam.ui.view.properti

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.view.properti.DestinasiEditProperti
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.jenis.HomeJenisViewModel
import com.example.finalpam.ui.viewmodel.manajer.HomeManajerViewModel
import com.example.finalpam.ui.viewmodel.pemilik.HomePemilikViewModel
import com.example.finalpam.ui.viewmodel.properti.EditPropertiViewModel
import kotlinx.coroutines.launch

object DestinasiEditProperti : DestinasiNavigasi {
    override val route = "editproperti"
    override val titleRes = "Edit Properti"
    const val id_properti = "id_properti"
    val routeWithArgs = "$route/{$id_properti}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPropertiView(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditPropertiViewModel = viewModel(factory = PenyediaViewModel.Factory),
    pemiliikViewModel : HomePemilikViewModel = viewModel ( factory = PenyediaViewModel.Factory),
    manajerViewModel: HomeManajerViewModel = viewModel(factory = PenyediaViewModel.Factory),
    jenisViewModel: HomeJenisViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    val pmlkUIState = pemiliikViewModel.pmlkUIState
    val selectedPemilik by remember { mutableStateOf(pemiliikViewModel.selectedPemilik) }

    val mnjrUIState = manajerViewModel.mnjrUIState
    val selectedManajer by remember { mutableStateOf(manajerViewModel.selectedManajer) }

    val jnsUIState = jenisViewModel.jnsUIState
    val selectedJenis by remember { mutableStateOf(jenisViewModel.selectedJenis) }

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEditProperti.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onPropertiValueChange = viewModel::updateInsertPrptiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.editProperti()
                    navigateBack()
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
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 1.dp) // Padding untuk margin kiri dan kanan
                .padding(bottom = 1.dp) // Jaga agar elemen tidak terpotong di bawah
                .verticalScroll(rememberScrollState()) // Scroll untuk konten panjang
        )
    }
}

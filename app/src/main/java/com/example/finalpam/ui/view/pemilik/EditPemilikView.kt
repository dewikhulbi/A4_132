package com.example.finalpam.ui.view.pemilik

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.view.pemilik.DestinasiEditPemilik
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.pemilik.EditPemilikViewModel
import kotlinx.coroutines.launch

object DestinasiEditPemilik : DestinasiNavigasi {
    override val route = "editpemilik"
    override val titleRes = "Edit Pemilik"
    const val id_pemilik = "id_pemilik"
    val routeWithArgs = "$route/{$id_pemilik}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPemilikView(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditPemilikViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Scaffold (
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEditPemilik.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ){ innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(scrollState) // Tambahkan scrolling
                .padding(horizontal = 30.dp, vertical = 1.dp) // Tambahkan padding
        ) {
            EntryBody(
                insertUiState = viewModel.uiState,
                onPemilikValueChange = viewModel::updateInsertPmlkState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.editPemilik()
                        navigateBack()
                    }
                },
                modifier = modifier
                    .padding(innerPadding)
                    .offset(y = (-103).dp)
            )
        }
    }
}
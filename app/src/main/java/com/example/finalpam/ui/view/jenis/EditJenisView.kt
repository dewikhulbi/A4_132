package com.example.finalpam.ui.view.jenis

import androidx.compose.foundation.layout.*
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
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.jenis.EditJenisViewModel
import kotlinx.coroutines.launch

object DestinasiEditJenis : DestinasiNavigasi {
    override val route = "editjenis"
    override val titleRes = "Edit Jenis"
    const val id_jenis = "id_jenis"
    val routeWithArgs = "$route/{$id_jenis}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditJenisView(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditJenisViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEditJenis.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(scrollState) // Tambahkan scrolling
                .padding(horizontal = 30.dp, vertical = 55.dp) // Tambahkan padding
        ) {
            EntryBody(
                insertUiState = viewModel.uiState,
                onJenisValueChange = viewModel::updateInsertJnsState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.editJenis()
                        navigateBack()
                    }
                },
                modifier = Modifier.offset(y = (-70).dp)
            )
        }
    }
}

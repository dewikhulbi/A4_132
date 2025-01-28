package com.example.finalpam.ui.view.manajer

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.view.jenis.EntryBody
import com.example.finalpam.ui.view.manajer.DestinasiEditManajer
import com.example.finalpam.ui.view.manajer.EntryBody
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.manajer.EditManajerViewModel
import kotlinx.coroutines.launch

object DestinasiEditManajer : DestinasiNavigasi {
    override val route = "editmanajer"
    override val titleRes = "Edit Manajer"
    const val id_manajer = "id_manajer"
    val routeWithArgs = "$route/{$id_manajer}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditManajerView(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditManajerViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEditManajer.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ){ innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onManajerValueChange = viewModel::updateInsertMnjrState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.editManajer()
                    navigateBack()
                }
            },
            modifier = modifier
                .padding(innerPadding)
                .offset(y = (-10).dp)
        )

    }
}
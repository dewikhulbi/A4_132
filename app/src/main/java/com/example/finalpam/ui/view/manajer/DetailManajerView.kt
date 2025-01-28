package com.example.finalpam.ui.view.manajer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.model.Manajer
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.view.manajer.DestinasiDetailManajer
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.manajer.DetailManajerViewModel
import com.example.finalpam.ui.viewmodel.manajer.DetailMnjrUiState

object DestinasiDetailManajer : DestinasiNavigasi {
    override val route = "detailmanajer"
    override val titleRes = "Detail Manajer"
    const val id_manajer = "id_manajer"
    val routeWithArgs = "$route/{$id_manajer}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailManajerView(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit,
    detailmanajerViewModel: DetailManajerViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailManajer.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val id_manajer = (detailmanajerViewModel.detailMnjrUiState as? DetailMnjrUiState.Success)?.manajer?.id_manajer
                    if (id_manajer != null) onEditClick(id_manajer)
                },
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Manajer",
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding).offset(y = (-70).dp)
        ) {
            DetailStatus(
                mnjrUiState = detailmanajerViewModel.detailMnjrUiState,
                retryAction = { detailmanajerViewModel.getMnjrById() },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun DetailStatus(
    mnjrUiState: DetailMnjrUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (mnjrUiState) {
        is DetailMnjrUiState.Success -> {
            DetailCard(
                manajer = mnjrUiState.manajer,
                modifier = modifier.padding(16.dp)
            )
        }

        is DetailMnjrUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is DetailMnjrUiState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Terjadi kesalahan.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = retryAction) {
                        Text(text = "Coba Lagi")
                    }
                }
            }
        }
    }
}

@Composable
fun DetailCard(
    manajer: Manajer,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            //horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ComponentDetailMnjr(judul = "Id_Manajer", isinya = manajer.id_manajer)
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailMnjr(judul = "Nama_Manajer", isinya = manajer.nama_manajer)
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailMnjr(judul = "Kontak_Manajer", isinya = manajer.kontak_manajer)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ComponentDetailMnjr(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$judul:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
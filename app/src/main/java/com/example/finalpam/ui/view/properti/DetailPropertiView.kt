package com.example.finalpam.ui.view.properti

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
import com.example.finalpam.model.Properti
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.view.properti.DestinasiDetailProperti
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.properti.DetailPropertiViewModel
import com.example.finalpam.ui.viewmodel.properti.DetailPrptiUiState

object DestinasiDetailProperti : DestinasiNavigasi {
    override val route = "detailproperti"
    override val titleRes = "Detail Properti"
    const val id_properti = "id_properti"
    val routeWithArgs = "$route/{$id_properti}"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPropertiView(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit,
    detailpropertiViewModel: DetailPropertiViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailProperti.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val id_properti = (detailpropertiViewModel.detailPrptiUiState as? DetailPrptiUiState.Success)?.properti?.id_properti
                    if (id_properti != null) onEditClick(id_properti)
                },
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Properti",
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
                prptiUiState = detailpropertiViewModel.detailPrptiUiState,
                retryAction = { detailpropertiViewModel.getPrptiById() },
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
    prptiUiState: DetailPrptiUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (prptiUiState) {
        is DetailPrptiUiState.Success -> {
            DetailCard(
                properti = prptiUiState.properti,
                modifier = modifier.padding(16.dp)
            )
        }

        is DetailPrptiUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is DetailPrptiUiState.Error -> {
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
    properti: Properti,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(25.dp),
            //horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ComponentDetailPrpti(judul = "Id_Properti", isinya = properti.id_properti)
            Spacer(modifier = Modifier.height(3.dp))
            ComponentDetailPrpti(judul = "Nama_Properti", isinya = properti.nama_properti)
            Spacer(modifier = Modifier.height(3.dp))
            ComponentDetailPrpti(judul = "deskripsi_properti", isinya = properti.deskripsi_properti)
            Spacer(modifier = Modifier.height(3.dp))
            ComponentDetailPrpti(judul = "lokasi", isinya = properti.lokasi)
            Spacer(modifier = Modifier.height(3.dp))
            ComponentDetailPrpti(judul = "harga", isinya = properti.harga)
            Spacer(modifier = Modifier.height(3.dp))
            ComponentDetailPrpti(judul = "status_properti", isinya = properti.status_properti)
            Spacer(modifier = Modifier.height(3.dp))
            ComponentDetailPrpti(judul = "id_jenis", isinya = properti.id_jenis)
            Spacer(modifier = Modifier.height(3.dp))
            ComponentDetailPrpti(judul = "id_pemilik", isinya = properti.id_pemilik)
            Spacer(modifier = Modifier.height(3.dp))
            ComponentDetailPrpti(judul = "id_manajer", isinya = properti.id_manajer)
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}

@Composable
fun ComponentDetailPrpti(
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
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
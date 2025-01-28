package com.example.finalpam.ui.view.jenis

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.model.Jenis
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.jenis.HomeJenisViewModel
import com.example.finalpam.ui.viewmodel.jenis.HomeJenisUiState
import com.example.finalproject.R

object  DestinasiHomeJenis  : DestinasiNavigasi {
    override val  route  =  "homejenis"
    override val  titleRes  =  "Home Jenis"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun  HomeJenisScreen (
    navigateToItemEntry :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDetailClick :  ( String )  ->  Unit  =  {},
    viewModel : HomeJenisViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val  scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiHomeJenis .titleRes,
                canNavigateBack =  false ,
                scrollBehavior =  scrollBehavior ,
                onRefresh =  {
                    viewModel . getJns ()
                }
            )
        } ,
        floatingActionButton =  {
            FloatingActionButton (
                onClick =  navigateToItemEntry ,
                shape =  MaterialTheme . shapes .medium,
                modifier =  Modifier . padding ( 18 . dp )
            )  {
                Icon ( imageVector =  Icons .Default. Add ,  contentDescription =  "Add Kontak" )

            }
        } ,
    )  {  innerPadding  ->
        HomeStatus (
            homeUiState =  viewModel .jnsUIState,
            retryAction =  {  viewModel . getJns ()  } ,  modifier =  Modifier . padding ( innerPadding ),
            onDetailClick =  onDetailClick ,  onDeleteClick =  {
                viewModel . deleteJns ( it .id_jenis   )
                viewModel . getJns ()
            }
        )

    }


}

@Composable
fun  HomeStatus (
    homeUiState : HomeJenisUiState,
    retryAction :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDeleteClick :  (Jenis)  ->  Unit  =  {},
    onDetailClick :  ( String )  ->  Unit
) {

    when  ( homeUiState ) {
        is  HomeJenisUiState . Loading  ->  OnLoading ( modifier =  modifier . fillMaxSize ())

        is  HomeJenisUiState . Success  ->
            if  ( homeUiState .jenis. isEmpty ()){
                return  Box ( modifier =  modifier . fillMaxSize (),  contentAlignment =  Alignment .Center)  {
                    Text ( text =  "Tidak ada data Kontak"  )

                }
            } else  {
                JnsLayout (

                    jenis =  homeUiState .jenis,  modifier =  modifier . fillMaxWidth (),
                    onDetailClick =  {
                        onDetailClick ( it .id_jenis)
                    },
                    onDeleteClick =  {
                        onDeleteClick ( it )
                    }
                )
            }
        is  HomeJenisUiState . Error  ->  OnError ( retryAction ,  modifier =  modifier . fillMaxSize ())
    }

}

@Composable
fun  OnLoading ( modifier : Modifier =  Modifier ) {
    Image (
        modifier =  modifier.size ( 200 . dp ),
        painter =  painterResource ( R.drawable.loading_img),
        contentDescription =  stringResource ( R.string.loading )
    )
}

@Composable
fun  OnError ( retryAction :  ()  ->  Unit ,  modifier : Modifier =  Modifier ) {
    Column (
        modifier =  modifier ,
        verticalArrangement =  Arrangement .Center,
        horizontalAlignment =  Alignment .CenterHorizontally
    )  {
        Image (
            painter =  painterResource ( id =  R . drawable . ic_connection_error ),  contentDescription =  ""
        )
        Text ( text =  stringResource ( R . string . loading_failed ),  modifier =  Modifier . padding ( 16 . dp ))
        Button ( onClick =  retryAction )  {
            Text ( stringResource ( R . string . retry ))
        }
    }
}

@Composable
fun  JnsLayout (
    jenis :  List <Jenis>,
    modifier : Modifier =  Modifier,
    onDetailClick :  ( Jenis )  ->  Unit,
    onDeleteClick :  ( Jenis )  ->  Unit  =  {}
) {
    LazyColumn (
        modifier =  modifier ,
        contentPadding =  PaddingValues ( 16 . dp ),
        verticalArrangement =  Arrangement . spacedBy ( 16 . dp )
    )  {
        items(jenis) { jns ->
            JnsCard(
                jenis = jns,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(jns) },
                onDeleteClick = {
                    onDeleteClick(jns)
                }
            )
        }

    }

}

@Composable
fun  JnsCard (
    jenis :  Jenis,
    modifier : Modifier =  Modifier,
    onDeleteClick :  ( Jenis )  ->  Unit  =  {}
) {
    Card (
        modifier =  modifier ,
        shape =  MaterialTheme . shapes .medium,
        elevation =  CardDefaults . cardElevation ( defaultElevation =  8 . dp )
    )  {
        Column (
            modifier =  Modifier . padding ( 16 . dp ),
            verticalArrangement =  Arrangement . spacedBy ( 8 . dp )
        )  {
            Row (
                modifier =  Modifier . fillMaxWidth (),
                verticalAlignment =  Alignment .CenterVertically
            )  {
                Text (
                    text =  jenis .id_jenis,
                    style =  MaterialTheme . typography .titleLarge,

                    )
                Spacer ( Modifier . weight ( 1f ))
                IconButton ( onClick =  {  onDeleteClick ( jenis )  } )  {
                    Icon (
                        imageVector =  Icons .Default. Delete ,
                        contentDescription =  null ,
                    )
                }
                Text (
                    text =  jenis .nama_jenis,
                    style =  MaterialTheme . typography .titleMedium
                )
            }

            Text (
                text =  jenis .deskripsi_jenis,
                style =  MaterialTheme . typography .titleMedium
            )
        }

    }
}
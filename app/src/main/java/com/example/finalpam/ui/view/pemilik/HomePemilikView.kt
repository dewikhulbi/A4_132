package com.example.finalpam.ui.view.pemilik

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
import com.example.finalpam.model.Pemilik
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.view.pemilik.DestinasiHomePemilik
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.pemilik.HomePemilikUiState
import com.example.finalpam.ui.viewmodel.pemilik.HomePemilikViewModel
import com.example.finalproject.R

object  DestinasiHomePemilik  : DestinasiNavigasi {
    override val  route  =  "homepemilik"
    override val  titleRes  =  "Home Pemilik"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun  HomePemilikScreen (
    navigateToItemEntry :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDetailClick :  ( String )  ->  Unit  =  {},
    viewModel : HomePemilikViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val  scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiHomePemilik .titleRes,
                canNavigateBack =  false ,
                scrollBehavior =  scrollBehavior ,
                onRefresh =  {
                    viewModel . getPmlk ()
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
            homeUiState =  viewModel .pmlkUIState,
            retryAction =  {  viewModel . getPmlk ()  } ,  modifier =  Modifier . padding ( innerPadding ),
            onDetailClick =  onDetailClick ,  onDeleteClick =  {
                viewModel . deletePmlk ( it .id_pemilik   )
                viewModel . getPmlk ()
            }
        )

    }


}

@Composable
fun  HomeStatus (
    homeUiState : HomePemilikUiState,
    retryAction :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDeleteClick :  (Pemilik)  ->  Unit  =  {},
    onDetailClick :  ( String )  ->  Unit
) {

    when  ( homeUiState ) {
        is  HomePemilikUiState. Loading  ->  OnLoading ( modifier =  modifier . fillMaxSize ())

        is  HomePemilikUiState. Success  ->
            if  ( homeUiState .pemilik. isEmpty ()){
                return  Box ( modifier =  modifier . fillMaxSize (),  contentAlignment =  Alignment .Center)  {
                    Text ( text =  "Tidak ada data Kontak"  )

                }
            } else  {
                PmlkLayout (

                    pemilik =  homeUiState .pemilik,  modifier =  modifier . fillMaxWidth (),
                    onDetailClick =  {
                        onDetailClick ( it .id_pemilik)
                    },
                    onDeleteClick =  {
                        onDeleteClick ( it )
                    }
                )
            }
        is  HomePemilikUiState. Error  ->  OnError ( retryAction ,  modifier =  modifier . fillMaxSize ())
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
fun  PmlkLayout (
    pemilik :  List <Pemilik>,
    modifier : Modifier =  Modifier,
    onDetailClick :  (Pemilik)  ->  Unit,
    onDeleteClick :  (Pemilik)  ->  Unit  =  {}
) {
    LazyColumn (
        modifier =  modifier ,
        contentPadding =  PaddingValues ( 16 . dp ),
        verticalArrangement =  Arrangement . spacedBy ( 16 . dp )
    )  {
        items(pemilik) { pmlk ->
            PmlkCard(
                pemilik = pmlk,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pmlk) },
                onDeleteClick = {
                    onDeleteClick(pmlk)
                }
            )
        }

    }

}

@Composable
fun  PmlkCard (
    pemilik : Pemilik,
    modifier : Modifier =  Modifier,
    onDeleteClick :  (Pemilik)  ->  Unit  =  {}
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
                    text =  pemilik .id_pemilik,
                    style =  MaterialTheme . typography .titleLarge,

                    )
                Spacer ( Modifier . weight ( 1f ))
                IconButton ( onClick =  {  onDeleteClick ( pemilik )  } )  {
                    Icon (
                        imageVector =  Icons .Default. Delete ,
                        contentDescription =  null ,
                    )
                }
                Text (
                    text =  pemilik .nama_pemilik,
                    style =  MaterialTheme . typography .titleMedium
                )
            }

            Text (
                text =  pemilik .kontak_pemilik,
                style =  MaterialTheme . typography .titleMedium
            )
        }

    }
}
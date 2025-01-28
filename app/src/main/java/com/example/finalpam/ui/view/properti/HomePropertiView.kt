package com.example.finalpam.ui.view.properti

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
import com.example.finalpam.model.Properti
import com.example.finalpam.ui.CostumeWidget.CostumeTopAppBar
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalpam.ui.view.properti.DestinasiHomeProperti
import com.example.finalpam.ui.viewmodel.PenyediaViewModel
import com.example.finalpam.ui.viewmodel.jenis.HomeJenisViewModel
import com.example.finalpam.ui.viewmodel.manajer.HomeManajerViewModel
import com.example.finalpam.ui.viewmodel.pemilik.HomePemilikViewModel
import com.example.finalpam.ui.viewmodel.properti.HomePropertiUiState
import com.example.finalpam.ui.viewmodel.properti.HomePropertiViewModel
import com.example.finalproject.R

object  DestinasiHomeProperti  : DestinasiNavigasi {
    override val  route  =  "homeproperti"
    override val  titleRes  =  "Home Properti"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun  HomePropertiScreen (
    navigateToItemEntry :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDetailClick :  ( String )  ->  Unit  =  {},
    viewModel : HomePropertiViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val  scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiHomeProperti .titleRes,
                canNavigateBack =  false ,
                scrollBehavior =  scrollBehavior ,
                onRefresh =  {
                    viewModel . getPrpti ()
                }
            )
        } ,
        floatingActionButton =  {
            FloatingActionButton (
                onClick =  navigateToItemEntry ,
                shape =  MaterialTheme . shapes .medium,
                modifier =  Modifier . padding ( 10 . dp )
            )  {
                Icon ( imageVector =  Icons .Default. Add ,  contentDescription =  "Add Properti" )

            }
        } ,
    )  {  innerPadding  ->
        HomeStatus (
            homeUiState =  viewModel .prptiUIState,
            retryAction =  {  viewModel . getPrpti ()  } ,  modifier =  Modifier . padding ( innerPadding ),
            onDetailClick =  onDetailClick ,  onDeleteClick =  {
                viewModel . deletePrpti ( it .id_properti   )
                viewModel . getPrpti ()
            }
        )

    }


}

@Composable
fun  HomeStatus (
    homeUiState : HomePropertiUiState,
    retryAction :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDeleteClick :  (Properti)  ->  Unit  =  {},
    onDetailClick :  ( String )  ->  Unit
) {

    when  ( homeUiState ) {
        is  HomePropertiUiState. Loading  ->  OnLoading ( modifier =  modifier . fillMaxSize ())

        is  HomePropertiUiState. Success  ->
            if  ( homeUiState .properti. isEmpty ()){
                return  Box ( modifier =  modifier . fillMaxSize (),  contentAlignment =  Alignment .Center)  {
                    Text ( text =  "Tidak ada data Properti"  )

                }
            } else  {
                PrptiLayout (

                    properti =  homeUiState .properti,  modifier =  modifier . fillMaxWidth (),
                    onDetailClick =  {
                        onDetailClick ( it .id_properti)
                    },
                    onDeleteClick =  {
                        onDeleteClick ( it )
                    }
                )
            }
        is  HomePropertiUiState. Error  ->  OnError ( retryAction ,  modifier =  modifier . fillMaxSize ())
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
        Text ( text =  stringResource ( R . string . loading_failed ),  modifier =  Modifier . padding ( 10 . dp ))
        Button ( onClick =  retryAction )  {
            Text ( stringResource ( R . string . retry ))
        }
    }
}

@Composable
fun  PrptiLayout (
    properti :  List <Properti>,
    modifier : Modifier =  Modifier,
    onDetailClick :  (Properti)  ->  Unit,
    onDeleteClick :  (Properti)  ->  Unit  =  {}
) {
    LazyColumn (
        modifier =  modifier ,
        contentPadding =  PaddingValues ( 16 . dp ),
        verticalArrangement =  Arrangement . spacedBy ( 16 . dp )
    )  {
        items(properti) { prpti ->
            PrptiCard(
                properti = prpti,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(prpti) },
                onDeleteClick = {
                    onDeleteClick(prpti)
                }
            )
        }

    }

}

@Composable
fun  PrptiCard (
    properti : Properti,
    modifier : Modifier =  Modifier,
    onDeleteClick :  (Properti)  ->  Unit  =  {}
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
                    text =  properti .id_properti,
                    style =  MaterialTheme . typography .titleLarge,

                    )
                Spacer ( Modifier . weight ( 1f ))
                IconButton ( onClick =  {  onDeleteClick ( properti )  } )  {
                    Icon (
                        imageVector =  Icons .Default. Delete ,
                        contentDescription =  null ,
                    )
                }
                Text (
                    text =  properti .nama_properti,
                    style =  MaterialTheme . typography .titleMedium
                )
            }

            Text (
                text =  properti .deskripsi_properti,
                style =  MaterialTheme . typography .titleMedium
            )
        }

    }
}
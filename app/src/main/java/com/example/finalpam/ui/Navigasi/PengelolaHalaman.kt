package com.example.finalpam.ui.Navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalpam.ui.view.DestinasiMenu
import com.example.finalpam.ui.view.HomeMenuView
import com.example.finalpam.ui.view.jenis.DestinasiDetailJenis
import com.example.finalpam.ui.view.jenis.DestinasiEditJenis
import com.example.finalpam.ui.view.jenis.DestinasiEntryJenis
import com.example.finalpam.ui.view.jenis.DestinasiHomeJenis
import com.example.finalpam.ui.view.jenis.DetailJenisView
import com.example.finalpam.ui.view.jenis.EditJenisView
import com.example.finalpam.ui.view.jenis.EntryJnsScreen
import com.example.finalpam.ui.view.jenis.HomeJenisScreen
import com.example.finalpam.ui.view.manajer.DestinasiDetailManajer
import com.example.finalpam.ui.view.manajer.DestinasiEditManajer
import com.example.finalpam.ui.view.manajer.DestinasiEntryManajer
import com.example.finalpam.ui.view.manajer.DestinasiHomeManajer
import com.example.finalpam.ui.view.manajer.DetailManajerView
import com.example.finalpam.ui.view.manajer.EditManajerView
import com.example.finalpam.ui.view.manajer.EntryMnjrScreen
import com.example.finalpam.ui.view.manajer.HomeManajerScreen
import com.example.finalpam.ui.view.pemilik.DestinasiDetailPemilik
import com.example.finalpam.ui.view.pemilik.DestinasiEditPemilik
import com.example.finalpam.ui.view.pemilik.DestinasiEntryPemilik
import com.example.finalpam.ui.view.pemilik.DestinasiHomePemilik
import com.example.finalpam.ui.view.pemilik.DetailPemilikView
import com.example.finalpam.ui.view.pemilik.EditPemilikView
import com.example.finalpam.ui.view.pemilik.EntryPmlkScreen
import com.example.finalpam.ui.view.pemilik.HomePemilikScreen
import com.example.finalpam.ui.view.properti.DestinasiDetailProperti
import com.example.finalpam.ui.view.properti.DestinasiEditProperti
import com.example.finalpam.ui.view.properti.DestinasiEntryProperti
import com.example.finalpam.ui.view.properti.DestinasiHomeProperti
import com.example.finalpam.ui.view.properti.DetailPropertiView
import com.example.finalpam.ui.view.properti.EditPropertiView
import com.example.finalpam.ui.view.properti.EntryPrptiScreen
import com.example.finalpam.ui.view.properti.HomePropertiScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiMenu.route,
        modifier = Modifier
    ) {
        composable(
            route = DestinasiMenu.route
        ){
            HomeMenuView(
                onPropertiClick = {
                    navController.navigate(DestinasiHomeProperti.route)
                },
                onPemilikClick = {
                    navController.navigate(DestinasiHomePemilik.route)
                },
                onManajerClick = {
                    navController.navigate(DestinasiHomeManajer.route)
                },
                onJenisClick = {
                    navController.navigate(DestinasiHomeJenis.route)
                }
            )
        }

        composable(
            route = DestinasiHomeManajer.route
        ){
            HomeManajerScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryManajer.route) },
                onDetailClick = { id_manajer ->
                    navController.navigate("${DestinasiDetailManajer.route}/$id_manajer")
                    println(id_manajer)
                }
            )
        }

        composable(
            route = DestinasiEntryManajer.route
        ){
            EntryMnjrScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomeManajer.route) {
                        popUpTo(DestinasiHomeManajer.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetailManajer.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailManajer.id_manajer){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val id_manajer = backStackEntry.arguments?.getString(DestinasiDetailManajer.id_manajer)
            id_manajer?.let {
                DetailManajerView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { id_manajer ->
                        navController.navigate("${DestinasiEditManajer.route}/$id_manajer")
                        println(id_manajer)
                    }
                )
            }
        }
        composable(
            route = DestinasiEditManajer.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditManajer.id_manajer){
                type = NavType.StringType
            })
        ){
            EditManajerView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEditManajer.route
                    ){
                        popUpTo(DestinasiHomeManajer.route){
                            inclusive = true
                        }
                    }
                }
            )
        }















        composable(
            route = DestinasiHomePemilik.route
        ){
            HomePemilikScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryPemilik.route) },
                onDetailClick = { id_pemilik ->
                    navController.navigate("${DestinasiDetailPemilik.route}/$id_pemilik")
                    println(id_pemilik)
                }
            )
        }

        composable(
            route = DestinasiEntryPemilik.route
        ){
            EntryPmlkScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomePemilik.route) {
                        popUpTo(DestinasiHomePemilik.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetailPemilik.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPemilik.id_pemilik){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val id_pemilik = backStackEntry.arguments?.getString(DestinasiDetailPemilik.id_pemilik)
            id_pemilik?.let {
                DetailPemilikView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { id_pemilik ->
                        navController.navigate("${DestinasiEditPemilik.route}/$id_pemilik")
                        println(id_pemilik)
                    }
                )
            }
        }
        composable(
            route = DestinasiEditPemilik.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditPemilik.id_pemilik){
                type = NavType.StringType
            })
        ){
            EditPemilikView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEditPemilik.route
                    ){
                        popUpTo(DestinasiHomePemilik.route){
                            inclusive = true
                        }
                    }
                }
            )
        }


















        composable(
            route = DestinasiHomeProperti.route
        ){
            HomePropertiScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryProperti.route) },
                onDetailClick = { id_properti ->
                    navController.navigate("${DestinasiDetailProperti.route}/$id_properti")
                    println(id_properti)
                }
            )
        }

        composable(
            route = DestinasiEntryProperti.route
        ){
            EntryPrptiScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomeProperti.route) {
                        popUpTo(DestinasiHomeProperti.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetailProperti.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailProperti.id_properti){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val id_properti = backStackEntry.arguments?.getString(DestinasiDetailProperti.id_properti)
            id_properti?.let {
                DetailPropertiView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { id_properti ->
                        navController.navigate("${DestinasiEditProperti.route}/$id_properti")
                        println(id_properti)
                    }
                )
            }
        }
        composable(
            route = DestinasiEditProperti.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditProperti.id_properti){
                type = NavType.StringType
            })
        ){
            EditPropertiView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEditProperti.route
                    ){
                        popUpTo(DestinasiHomeProperti.route){
                            inclusive = true
                        }
                    }
                }
            )
        }










        composable(
            route = DestinasiHomePemilik.route
        ){
            HomePemilikScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryPemilik.route) },
                onDetailClick = { id_pemilik ->
                    navController.navigate("${DestinasiDetailPemilik.route}/$id_pemilik")
                    println(id_pemilik)
                }
            )
        }

        composable(
            route = DestinasiEntryPemilik.route
        ){
            EntryPmlkScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomePemilik.route) {
                        popUpTo(DestinasiHomePemilik.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetailPemilik.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPemilik.id_pemilik){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val id_pemilik = backStackEntry.arguments?.getString(DestinasiDetailPemilik.id_pemilik)
            id_pemilik?.let {
                DetailPemilikView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { id_pemilik ->
                        navController.navigate("${DestinasiEditPemilik.route}/$id_pemilik")
                        println(id_pemilik)
                    }
                )
            }
        }
        composable(
            route = DestinasiEditPemilik.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditPemilik.id_pemilik){
                type = NavType.StringType
            })
        ){
            EditPemilikView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEditPemilik.route
                    ){
                        popUpTo(DestinasiHomePemilik.route){
                            inclusive = true
                        }
                    }
                }
            )
        }





        composable(
            route = DestinasiHomeJenis.route
        ){
            HomeJenisScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryJenis.route) },
                onDetailClick = { id_jenis ->
                    navController.navigate("${DestinasiDetailJenis.route}/$id_jenis")
                    println(id_jenis)
                }
            )
        }

        composable(
            route = DestinasiEntryJenis.route
        ){
            EntryJnsScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomeJenis.route) {
                        popUpTo(DestinasiHomeJenis.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetailJenis.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailJenis.id_jenis){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val id_jenis = backStackEntry.arguments?.getString(DestinasiDetailJenis.id_jenis)
            id_jenis?.let {
                DetailJenisView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { id_jenis ->
                        navController.navigate("${DestinasiEditJenis.route}/$id_jenis")
                        println(id_jenis)
                    }
                )
            }
        }
        composable(
            route = DestinasiEditJenis.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditJenis.id_jenis){
                type = NavType.StringType
            })
        ){
            EditJenisView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEditJenis.route
                    ){
                        popUpTo(DestinasiHomeJenis.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
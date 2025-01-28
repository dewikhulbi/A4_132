package com.example.finalpam.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalpam.ui.Navigasi.DestinasiNavigasi
import com.example.finalproject.R

object DestinasiMenu : DestinasiNavigasi {
    override val route = "destinasi_menu"
    override val titleRes = "Menu Aplikasi"
}

@Composable
fun HomeMenuView(
    onPemilikClick: () -> Unit,
    onJenisClick: () -> Unit,
    onManajerClick: () -> Unit,
    onPropertiClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(1.dp)) // Ruang atas dikurangi untuk mendekatkan elemen ke atas

        // Logo aplikasi di bagian atas dengan ukuran lebih besar
        Image(
            painter = painterResource(id = R.drawable.properti),
            contentDescription = "App Logo",
            modifier = Modifier.size(300.dp) // Ukuran logo
        )

        Spacer(modifier = Modifier.height(8.dp)) // Jarak antara logo dan judul diperkecil

        // Judul aplikasi lebih dekat dengan logo
        Text(
            text = "Properti UMY",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF38436C),
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol Menu dengan Ikon
        MenuButton("Menu Pemilik", Icons.Filled.Person, onPemilikClick)
        MenuButton("Menu Jenis", Icons.Filled.Home, onJenisClick)
        MenuButton("Menu Manager", Icons.Filled.List, onManajerClick)
        MenuButton("Menu Properti", Icons.Filled.ShoppingCart, onPropertiClick)
    }
}

@Composable
fun MenuButton(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(0.9f)
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(50.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}
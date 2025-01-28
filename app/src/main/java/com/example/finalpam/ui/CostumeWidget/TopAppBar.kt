package com.example.finalpam.ui.CostumeWidget

import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostumeTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {},
    onRefresh: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge, // Gaya teks sesuai tema
                color = MaterialTheme.colorScheme.onPrimary // Warna teks kontras
            )
        },
        actions = {
            IconButton(onClick = onRefresh) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    tint = MaterialTheme.colorScheme.onPrimary // Warna ikon kontras
                )
            }
        },
        modifier = modifier.offset(y = (-20).dp),
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Navigate Up",
                        tint = MaterialTheme.colorScheme.onPrimary // Warna ikon kontras
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, // Warna background top app bar
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary, // Warna ikon navigasi
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary, // Warna ikon aksi
            titleContentColor = MaterialTheme.colorScheme.onPrimary // Warna teks judul
        )
    )
}

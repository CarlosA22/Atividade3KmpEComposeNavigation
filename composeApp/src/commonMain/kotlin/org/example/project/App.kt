package org.example.project

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "list"
        ) {
            composable("list") {
                DeviceListScreen(
                    onDeviceClick = { deviceName ->
                        navController.navigate("details/$deviceName")
                    }
                )
            }
            composable(
                route = "details/{deviceName}",
                arguments = listOf(navArgument("deviceName") { type = NavType.StringType })
            ) { backStackEntry ->
                val deviceName = backStackEntry.arguments?.getString("deviceName") ?: ""
                DeviceDetailScreen(
                    deviceName = deviceName,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun DeviceListScreen(onDeviceClick: (String) -> Unit) {
    val devices = listOf("iPhone 15", "Galaxy S24", "Pixel 8")

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(title = { Text("Lista de Dispositivos") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            items(devices) { device ->
                ListItem(
                    headlineContent = { Text(device) },
                    modifier = Modifier.clickable { onDeviceClick(device) }
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun DeviceDetailScreen(deviceName: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("Detalhes") },
                navigationIcon = {
                    Button(onClick = onBack) {
                        Text("Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = deviceName,
                    fontSize = 32.sp,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onBack) {
                    Text("Voltar")
                }
            }
        }
    }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package edu.ulsa.explorarui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.ulsa.explorarui.ui.theme.ExplorarUITheme
import  androidx.compose.runtime.setValue
import  androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExplorarUITheme {
                AppRoot()
            }
        }
    }
}



@Composable
fun AppRoot(){
    val snackbarHostState = remember { SnackbarHostState() }
    var contador by remember { mutableStateOf(0) }

    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Explorar UI")})
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { contador++ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier.padding(innerPadding),
            onShowSnackBar = {
                LaunchedEffect(contador) {
                    snackbarHostState.showSnackbar(message = "Clicks: $contador")
                }
            }
        )
    }
}


@Composable
fun HomeScreen(modifier : Modifier = Modifier, onShowSnackBar: () -> Unit){
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Button(onClick = onShowSnackBar) { Text(text="Mostrar SncakBar")}
    }
}

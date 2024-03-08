package com.radiant.spulab01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.radiant.spulab01.ui.screens.LoginScreen
import com.radiant.spulab01.ui.theme.SPULab01Theme
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.lifecycle.viewmodel.compose.viewModel
import com.radiant.spulab01.viewmodels.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: LoginViewModel = viewModel()
            viewModel.registerFileCreationLauncher(registerForActivityResult(StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val fileUri = result.data?.data ?: return@registerForActivityResult
                    viewModel.saveFileToUri(this, fileUri)
                }
            })
            SPULab01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SPULab01Theme {
        Greeting("Android")
    }
}
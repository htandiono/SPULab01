package com.radiant.spulab01.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.radiant.spulab01.viewmodels.LoginViewModel

@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = viewModel()
    var operatorName by remember { mutableStateOf(viewModel.operatorName) }
    var showErrorMessage by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SPULab01 - Receiving",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = operatorName,
            onValueChange = {
                operatorName = it
                viewModel.onOperatorNameChange(it)
            },
            label = { Text("Nama Operator") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(48.dp)) // Increased spacing
        Button(
            onClick = {
                if (viewModel.validateOperatorName()) {
                    viewModel.createNewFile(context)
                } else {
                    showErrorMessage = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("New File")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (viewModel.validateOperatorName()) {
                    // Navigate to new file screen (implementation later)
                } else {
                    showErrorMessage = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load File")
        }
    }

    // Error Dialog
    if (showErrorMessage) {
        AlertDialog(
            onDismissRequest = {
                showErrorMessage = false
            }, // Dismiss on clicking outside or back button
            title = { Text("Error") },
            text = { Text("Nama Operator tidak boleh kosong!") },
            confirmButton = {
                Button(onClick = { showErrorMessage = false }) {
                    Text("Ok")
                }
            }
        )
    }
}
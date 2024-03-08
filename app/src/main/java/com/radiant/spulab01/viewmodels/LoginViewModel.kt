package com.radiant.spulab01.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.ViewModel
import com.radiant.spulab01.data.CSVUtil
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.content.ContentResolver
import androidx.activity.result.ActivityResultLauncher


class LoginViewModel : ViewModel() {

    var operatorName by mutableStateOf("")
        private set

    private val CREATE_FILE_REQUEST_CODE = 1

    fun onOperatorNameChange(newName: String) {
        operatorName = newName
    }

    fun validateOperatorName(): Boolean {
        return operatorName.isNotBlank()
    }

    fun createNewFile(context: Context) {
        if (validateOperatorName()) {
            val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"))
            val filename = "$currentDate-SPULab01-$operatorName.csv"
            createFileWithSAF(context, filename)
        } else {
            // ... show error message
        }
    }

    private lateinit var createFileLauncher: ActivityResultLauncher<Intent>

    fun registerFileCreationLauncher(launcher: ActivityResultLauncher<Intent>) {
        createFileLauncher = launcher
    }

    private fun createFileWithSAF(context: Context, filename: String) {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/csv"
            putExtra(Intent.EXTRA_TITLE, filename)
        }
        createFileLauncher.launch(intent)
    }

    // In your ViewModel:
    fun saveFileToUri(context: Context, fileUri: Uri) {
        context.contentResolver.openOutputStream(fileUri)?.use { outputStream ->
            CSVUtil.createNewCSV(outputStream) // Assuming CSVUtil is updated
        }
    }
}
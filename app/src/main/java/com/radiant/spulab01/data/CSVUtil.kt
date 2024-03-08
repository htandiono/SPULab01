package com.radiant.spulab01.data

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

object CSVUtil {

    fun createNewCSV(outputStream: OutputStream) {
        // Create a new Workbook
        val workbook = XSSFWorkbook()

        // Create a new Sheet
        val sheet = workbook.createSheet("Data")

        // ... Your existing CSV writing logic ...

        // Write to OutputStream
        workbook.write(outputStream)
        outputStream.close() // Close the stream
        workbook.close()
    }
}
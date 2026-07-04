package io.github.ComposeKit2.Helper.Input

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import java.util.Locale


@Composable
fun AppDatePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                val calendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        onValueChange(
                            String.format(
                                Locale.US,
                                "%04d-%02d-%02d",
                                year,
                                month + 1,
                                day
                            )
                        )
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            enabled = false,
            label = { Text(label) },
            trailingIcon = {
                Icon(Icons.Default.DateRange, null)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
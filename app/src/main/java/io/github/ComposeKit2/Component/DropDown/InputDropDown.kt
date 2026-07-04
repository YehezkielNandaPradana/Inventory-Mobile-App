package io.github.ComposeKit2.Component.DropDown

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AppDropdown(
    label: String? = null,
    selectedItem: T?,
    items: List<T>,
    itemLabel: (T) -> String,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Pilih",
    enabled: Boolean = true,
    emptyText: String = "Tidak ada data",
    loading: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp),
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor: Color = Color(0xFFD1D5DB)
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {

        label?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                if (enabled) expanded = !expanded
            }
        ) {

            OutlinedTextField(
                value = selectedItem?.let(itemLabel) ?: "",
                onValueChange = {},
                readOnly = true,
                enabled = enabled,
                placeholder = {
                    Text(placeholder)
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = shape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = focusedBorderColor,
                    unfocusedBorderColor = unfocusedBorderColor
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {

                when {
                    loading -> {
                        DropdownMenuItem(
                            text = {
                                Text("Memuat data...")
                            },
                            onClick = {}
                        )
                    }

                    items.isEmpty() -> {
                        DropdownMenuItem(
                            text = {
                                Text(emptyText)
                            },
                            onClick = {}
                        )
                    }

                    else -> {
                        items.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Text(itemLabel(item))
                                },
                                onClick = {
                                    onItemSelected(item)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
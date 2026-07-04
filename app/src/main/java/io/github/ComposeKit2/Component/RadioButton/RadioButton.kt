package io.github.ComposeKit2.Component.RadioButton

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> SimpleRadioGroup(
    title: String,
    items: List<T>,
    selected: T,
    onSelected: (T) -> Unit,
    label: (T) -> String = { it.toString() }
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            items.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelected(item) }
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = item == selected,
                        onClick = { onSelected(item) }
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(text = label(item))
                }
            }
        }
    }
}
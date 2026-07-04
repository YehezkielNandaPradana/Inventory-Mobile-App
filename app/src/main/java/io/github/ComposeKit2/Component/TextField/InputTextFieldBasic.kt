package io.github.ComposeKit2.Component.TextField

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,

    modifier: Modifier = Modifier,

    label: String? = null,
    placeholder: String = "",

    isError: Boolean = false,
    singleLine: Boolean = true,

    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,

    numeric: Boolean = false,

    keyboardOptions: KeyboardOptions = if (numeric) {
        KeyboardOptions(keyboardType = KeyboardType.Number)
    } else {
        KeyboardOptions.Default
    },

    keyboardActions: KeyboardActions = KeyboardActions.Default,

    focusedContainerColor: Color = Color.White,
    unfocusedContainerColor: Color = Color.White,
    disabledContainerColor: Color = Color.LightGray,
    focusedIndicatorColor: Color = Color.Blue,
    unfocusedIndicatorColor: Color = Color.Gray,

    shape: Shape = RoundedCornerShape(10.dp),

    enabled: Boolean = true
) {

    Column(modifier = modifier) {

        label?.let {
            Text(
                text = it,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
            )
        }

        OutlinedTextField(
            value = value,
            onValueChange = { input ->

                if (numeric) {
                    val filtered = input.filter { ch -> ch.isDigit() }
                    onValueChange(filtered)
                } else {
                    onValueChange(input)
                }
            },
            modifier = Modifier.fillMaxWidth(),

            isError = isError,
            placeholder = { Text(placeholder) },

            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,

            singleLine = singleLine,
            enabled = enabled,
            shape = shape,

            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,

            colors = TextFieldDefaults.colors(
                focusedContainerColor = focusedContainerColor,
                unfocusedContainerColor = unfocusedContainerColor,
                disabledContainerColor = disabledContainerColor,
                focusedIndicatorColor = focusedIndicatorColor,
                unfocusedIndicatorColor = unfocusedIndicatorColor
            )
        )
    }
}
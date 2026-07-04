package ComposeKit.dsl

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

object Input {

    @Composable
    fun email(
        value: String,
        onChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            placeholder = { Text("Email") },
            leadingIcon = {
                Icon(Icons.Default.Email, null, tint = Color(0xFF2563EB))
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )
    }

    @Composable
    fun password(
        value: String,
        onChange: (String) -> Unit,
        visible: Boolean,
        onToggle: () -> Unit
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            placeholder = { Text("Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, null, tint = Color(0xFF2563EB))
            },
            trailingIcon = {
                IconButton(onClick = onToggle) {
                    Icon(
                        if (visible) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        null
                    )
                }
            },
            visualTransformation =
                if (visible) VisualTransformation.None
                else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        )
    }
}

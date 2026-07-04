package ComposeKit.Button

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object UiButton {

    @Composable
    fun Button(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        variant: ButtonVariant = ButtonVariant.Primary,
        size: ButtonSize = ButtonSize.Medium,
        loading: Boolean = false,
        enabled: Boolean = true,
        backgroundColor: Color? = null,
        textColor: Color? = null,
        borderColor: Color? = null,
        radius: Dp = 14.dp
    ) {

    }
}
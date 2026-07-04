package ComposeKit.Button

import androidx.compose.ui.unit.*

sealed class ButtonSize {
    abstract val height: Dp
    abstract val textSize: TextUnit

    data object Small : ButtonSize() {
        override val height = 44.dp
        override val textSize = 12.sp
    }

    data object Medium : ButtonSize() {
        override val height = 52.dp
        override val textSize = 14.sp
    }

    data object Large : ButtonSize() {
        override val height = 60.dp
        override val textSize = 16.sp
    }

    data class Custom(
        override val height: Dp,
        override val textSize: TextUnit
    ) : ButtonSize()
}
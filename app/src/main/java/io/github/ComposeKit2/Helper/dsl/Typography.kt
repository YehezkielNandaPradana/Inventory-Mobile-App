package io.github.ComposeKit2.Helper.dsl

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTypography {

    @Composable
    fun Title(
        text: String,
        modifier: Modifier = Modifier
    ) {
        Text(
            text = text,
            modifier = modifier,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }

    @Composable
    fun Heading(
        text: String,
        modifier: Modifier = Modifier
    ) {
        Text(
            text = text,
            modifier = modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

    @Composable
    fun Body(
        text: String,
        modifier: Modifier = Modifier
    ) {
        Text(
            text = text,
            modifier = modifier,
            fontSize = 16.sp
        )
    }
}
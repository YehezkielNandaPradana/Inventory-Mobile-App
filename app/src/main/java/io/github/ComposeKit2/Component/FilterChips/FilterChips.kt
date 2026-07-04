package io.github.ComposeKit2.Component.FilterChips
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import io.github.naxx.newinventaris2.ui.theme.BrandBlue
import io.github.naxx.newinventaris2.ui.theme.BrandBlueDark

data class FilterChipItem(
    val label: String,
    val icon: ImageVector? = null
)

data class FilterChipTokens(
    val selectedGradient: Brush = Brush.linearGradient(
        colors = listOf(BrandBlue, BrandBlueDark)
    ),
    val selectedTextColor: Color = Color.White,
    val unselectedBackground: Color = Color(0xFFF1F5F9),
    val unselectedTextColor: Color = Color(0xFF64748B),
    val unselectedBorderColor: Color = Color(0xFFE2E8F0),
    val selectedElevation: Dp = 6.dp,
    val unselectedElevation: Dp = 0.dp,
    val chipHeight: Dp = 36.dp,
    val horizontalPadding: Dp = 16.dp,
    val verticalPadding: Dp = 8.dp,
    val fontSize: TextUnit = 13.sp,
    val iconSize: Dp = 15.dp
)

@Composable
fun SmartFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    tokens: FilterChipTokens = FilterChipTokens()
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.93f else 1f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 400f),
        label = "scale"
    )

    val elevation by animateDpAsState(
        targetValue = if (selected) tokens.selectedElevation else tokens.unselectedElevation,
        animationSpec = tween(200),
        label = "elevation"
    )

    val unselectedBg by animateColorAsState(
        targetValue = if (selected) Color.Transparent else tokens.unselectedBackground,
        animationSpec = tween(200),
        label = "bg"
    )

    val textColor by animateColorAsState(
        targetValue = if (selected) tokens.selectedTextColor else tokens.unselectedTextColor,
        animationSpec = tween(200),
        label = "textColor"
    )

    Box(
        modifier = modifier
            .scale(scale)
            .shadow(elevation = elevation, shape = CircleShape, ambientColor = Color(0xFF0794E6).copy(alpha = 0.3f), spotColor = Color(
                0xFF9D9DDC
            ).copy(alpha = 0.3f))
            .clip(CircleShape)
            .then(
                if (selected) Modifier.background(tokens.selectedGradient)
                else Modifier.background(unselectedBg)
            )
            .then(
                if (!selected) Modifier.border(1.dp, tokens.unselectedBorderColor, CircleShape)
                else Modifier
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .height(tokens.chipHeight)
            .padding(horizontal = tokens.horizontalPadding, vertical = tokens.verticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(tokens.iconSize)
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
            Text(
                text = label,
                color = textColor,
                fontSize = tokens.fontSize,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                maxLines = 1
            )
        }
    }
}

@Composable
fun FilterChips(
    items: List<FilterChipItem>,
    selectedLabel: String?,
    onSelect: (String?) -> Unit,
    modifier: Modifier = Modifier,
    allLabel: String = "Semua",
    tokens: FilterChipTokens = FilterChipTokens()
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            SmartFilterChip(
                label = allLabel,
                selected = selectedLabel == null,
                onClick = { onSelect(null) },
                tokens = tokens
            )
        }
        items(items) { chip ->
            SmartFilterChip(
                label = chip.label,
                selected = selectedLabel == chip.label,
                onClick = { onSelect(chip.label) },
                icon = chip.icon,
                tokens = tokens
            )
        }
    }
}
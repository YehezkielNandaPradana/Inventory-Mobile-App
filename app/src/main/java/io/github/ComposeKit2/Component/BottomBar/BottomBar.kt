package io.github.ComposeKit2.Component.BottomBar

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.HistoryToggleOff
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

private val ActiveBackgroundColor = Color(0xFFE0F2FE)
private val ActiveColor = Color(0xFF0284C7)
private val InactiveColor = Color(0xFF94A3B8)
data class BottomBarItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
fun SimpleBottomBar(navController: NavHostController) {
    val context = LocalContext.current

    val items = listOf(
        BottomBarItem("Beranda", "Beranda", Icons.Filled.List, Icons.Outlined.List),
        BottomBarItem("Riwayat", "Riwayat", Icons.Filled.History, Icons.Outlined.HistoryToggleOff)
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val selectedIndex = items.indexOfFirst { it.route == currentRoute }.takeIf { it >= 0 } ?: 0

    Surface(
        color = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            SimpleBottomBarLayout(
                items = items,
                selectedIndex = selectedIndex,
                onItemClick = { index ->
                    val item = items[index]
                    try {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                        }
                    } catch (_: Exception) {
                        Toast.makeText(context, "Halaman ${item.title} tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
private fun SimpleBottomBarLayout(
    items: List<BottomBarItem>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val itemWidth = maxWidth / items.size

        val indicatorOffset by animateDpAsState(
            targetValue = itemWidth * selectedIndex,
            animationSpec = spring(dampingRatio = 0.75f, stiffness = 350f),
            label = "indicatorOffset"
        )

        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .width(itemWidth)
                .height(56.dp)
                .padding(horizontal = 4.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(ActiveBackgroundColor)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            items.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    SimpleBottomBarItemView(
                        item = item,
                        selected = index == selectedIndex,
                        onClick = { onItemClick(index) }
                    )
                }
            }
        }
    }
}

@Composable
private fun SimpleBottomBarItemView(
    item: BottomBarItem,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val iconScale by animateFloatAsState(
        targetValue = if (selected) 1.15f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "iconScale"
    )

    val tint by animateColorAsState(
        targetValue = if (selected) ActiveColor else InactiveColor,
        animationSpec = tween(300),
        label = "tint"
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0.6f,
        animationSpec = tween(300),
        label = "textAlpha"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) { onClick() }
    ) {
        Icon(
            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.title,
            tint = tint,
            modifier = Modifier
                .size(24.dp)
                .scale(iconScale)
        )

        Box(modifier = Modifier.height(4.dp))

        Text(
            text = item.title,
            color = tint,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.alpha(textAlpha)
        )
    }
}
package io.github.naxx.newinventaris2.ui.Activities.BerandaScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.ComposeKit2.Component.Card.InteractiveCard
import io.github.ComposeKit2.Model.Items
import io.github.naxx.newinventaris2.ui.theme.BrandBlueDark
import io.github.naxx.newinventaris2.ui.theme.ErrorDark
import io.github.naxx.newinventaris2.ui.theme.InfoSoft
import io.github.naxx.newinventaris2.ui.theme.SuccessMain
import io.github.naxx.newinventaris2.ui.theme.WarningMain

@Composable
fun CardItemList(
    item: Items,
    onClick: () -> Unit
) {
    val StatusColor = when {
        item.availableStock == 0 -> ErrorDark
        item.availableStock <= 2 -> WarningMain
        else -> SuccessMain
    }
    InteractiveCard(
        onClick = onClick,
        content = {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = item.categoryName,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = item.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Row(
                        modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp)
                                .clip(RoundedCornerShape(50))
                                .background(StatusColor)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = when {
                                item.availableStock == 0 -> "Stock Habis"
                                item.availableStock <= 2 -> "Stok Terbatas"
                                else -> "Tersedia"
                            },
                            color = StatusColor
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .background(InfoSoft, RoundedCornerShape(12.dp))
                        .padding(15.dp)
                ){
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            text = "${item.availableStock}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = BrandBlueDark
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "/ ${item.totalStock} Unit",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    )
}
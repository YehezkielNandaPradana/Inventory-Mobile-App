package io.github.naxx.newinventaris2.ui.Activities.RiwayatScreen.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.EventBusy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.ComposeKit2.Component.Badge.AppBadge
import io.github.ComposeKit2.Component.Card.CleanCard
import io.github.ComposeKit2.Helper.FormatTanggal.toDateIndonesia
import io.github.ComposeKit2.Model.Borrowings
import io.github.ComposeKit2.ViewModel.ViewModel.RiwayatViewModel
import io.github.naxx.newinventaris2.ui.theme.BrandBlueDark
import io.github.naxx.newinventaris2.ui.theme.GradientSoftEnd
import io.github.naxx.newinventaris2.ui.theme.InfoDark
import io.github.naxx.newinventaris2.ui.theme.SuccessMain

@Composable
fun CardRiwayat(
    item: Borrowings,
    onReturnClick: (Int) -> Unit
) {
    val viewModel : RiwayatViewModel = viewModel()
    val StatusColor = when{
        item.status == "Borrowed" -> GradientSoftEnd
        item.status == "Returned" -> SuccessMain
        else -> Color.Gray
    }
    CleanCard(
        onClick = {
        },
        content = {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Row {
                    Text(
                        text = item.namaItem,
                        fontSize = 19.sp,
                        color = BrandBlueDark,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    AppBadge(
                        text = item.status,
                        backgroundColor = StatusColor,
                        contentColor = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.namaCategory,
                    fontSize = 14.sp,
                    color = InfoDark
                )
                Spacer(modifier = Modifier.height(7.dp))
                if (item.status == "Borrowed"){
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Tanggal Pinjam : ${item.borrowDate.toDateIndonesia()}",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                    Spacer(modifier = Modifier.height(7.dp))
                    Button(
                        onClick = {
                            onReturnClick(item.id)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandBlueDark
                        ),
                        modifier = Modifier.fillMaxWidth().height(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.EventBusy,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Kembalikan")
                    }
                }else{
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Tanggal Pinjam : ${item.borrowDate.toDateIndonesia()}",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                    Spacer(modifier = Modifier.width(7.dp))
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Tanggal Kembali : ${item.returnDate.toDateIndonesia()}",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    )
}
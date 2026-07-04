package io.github.naxx.newinventaris2.ui.Activities.BerandaScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import io.github.ComposeKit2.Component.BottomBar.SimpleBottomBar
import io.github.ComposeKit2.Component.DropDown.AppDropdown
import io.github.ComposeKit2.Component.FilterChips.FilterChipItem
import io.github.ComposeKit2.Component.FilterChips.FilterChips
import io.github.ComposeKit2.Component.SearchBar.SearchBar
import io.github.ComposeKit2.Component.TopBar.HomeTopBar
import io.github.ComposeKit2.Component.TopBar.InfoDark
import io.github.ComposeKit2.Component.TopBar.InfoMain
import io.github.ComposeKit2.Helper.Fetch.StateHandler
import io.github.ComposeKit2.Helper.Input.AppTextField
import io.github.ComposeKit2.Helper.Notify.AppNotifierHost
import io.github.ComposeKit2.Helper.PopUp.DialogHelper
import io.github.ComposeKit2.Model.Categories
import io.github.ComposeKit2.ViewModel.ViewModel.HomeViewModel
import io.github.naxx.newinventaris2.ui.theme.BrandBlueDark
import io.github.naxx.newinventaris2.ui.theme.InputFieldColor

@Composable
fun BerandaScreen(
    navController: NavHostController,
    viewModel: HomeViewModel,
) {
    val item by viewModel.items.state.collectAsStateWithLifecycle()
    val category by viewModel.categories.state.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    var selectedFilter by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            SimpleBottomBar(navController)
            AppNotifierHost()
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = BrandBlueDark,
                onClick = {
                    DialogHelper.showCustom {
                        Text(
                            text = "Tambahkan Barang Baru",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandBlueDark
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Silahkan Lengkapi Input Untuk Tambah Item",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        var selectedCategory by remember { mutableStateOf<Categories?>(null) }
                        StateHandler(
                            state = category,
                            emptyMessage = "Kategori tidak tersedia"
                        ) { categoryList ->
                            AppDropdown(
                                label = "Kategori",
                                selectedItem = selectedCategory,
                                items = categoryList,
                                itemLabel = { it.name },
                                onItemSelected = {
                                    selectedCategory = it
                                    viewModel.idCategory.value = it.id
                                }
                            )
                        }
                        AppTextField(
                            label = "Nama",
                            value = viewModel.name.value,
                            onValueChange = { viewModel.name.value = it },
                            placeholder = "Masukkan Nama"
                        )
                        AppTextField(
                            label = "Stok",
                            value = viewModel.stock.value,
                            onValueChange = { viewModel.stock.value = it },
                            placeholder = "Masukkan Jumlah Stok"
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            OutlinedButton(
                                onClick = {
                                    DialogHelper.close()
                                    viewModel.resetState()
                                },
                            ) {
                                Text("Batal")
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Button(
                                onClick = {
                                    viewModel.addItem()
                                },
                                elevation = ButtonDefaults.buttonElevation(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = BrandBlueDark
                                )
                            ) {
                                Text("Tambah")
                            }
                        }
                    }
                }
            ) {
                Icon(Icons.Default.Add, null, tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            SearchBar(
                query = searchQuery, onQueryChange = { query ->
                    viewModel.onSearchChange(query)
                })
            Column(
                modifier = Modifier.padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StateHandler(
                    state = category,
                    emptyMessage = "Tidak Ada kategori Yg Tersedia"
                ) { categoryList ->

                    FilterChips(
                        items = categoryList.map {
                            FilterChipItem(
                                label = it.name
                            )
                        },
                        selectedLabel = selectedFilter,
                        onSelect = { categories ->
                            selectedFilter = categories
                            viewModel.onSelectedCategory(categories)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    StateHandler(
                        state = item,
                        emptyMessage = "Tidak Ada Item Yg Tersedia"
                    ) { itemList ->
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(itemList) { item ->
                                CardItemList(item, onClick = {
                                    DialogHelper.showCustom {
                                        Text(
                                            text = "Konfirmasi Pinjam",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = BrandBlueDark
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(
                                            text = "Silahkan masukan email anda untuk memijam \n" + item.name,
                                            fontSize = 13.sp,
                                            color = Color.DarkGray
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Column(
                                            modifier = Modifier.padding(start = 15.dp)
                                        ) {
                                            Text(
                                                text = item.name,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "Tersedia : ${item.availableStock}",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = InfoDark
                                            )
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Text(
                                                text = "",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                            OutlinedTextField(
                                                value = viewModel.email.value,
                                                onValueChange = { viewModel.onEmailChange(it) },
                                                modifier = Modifier.fillMaxWidth(),
                                                isError = viewModel.emailValidation.isError,
                                                placeholder = { Text("Silahkan Masukan Email") },
                                                leadingIcon = {
                                                    Icon(Icons.Default.Email, contentDescription = null)
                                                },
                                                supportingText = {
                                                    if (viewModel.emailValidation.isError) {
                                                        Text(
                                                            text = viewModel.emailValidation.errorMessage,
                                                            color = Color.Red
                                                        )
                                                    }
                                                },
                                                shape = RoundedCornerShape(10.dp),
                                                colors = TextFieldDefaults.colors(
                                                    focusedContainerColor = InputFieldColor,
                                                    unfocusedContainerColor = InputFieldColor,
                                                    disabledContainerColor = InputFieldColor,
                                                    focusedIndicatorColor = InputFieldColor,
                                                    unfocusedIndicatorColor = Color.Gray,
                                                ),
                                                singleLine = true
                                            )
                                            Spacer(modifier = Modifier.height(15.dp))
                                            Row(
                                                modifier = Modifier.padding(12.dp)
                                            ) {
                                                OutlinedButton(
                                                    onClick = {
                                                        DialogHelper.close()
                                                        viewModel.resetState()
                                                    },
                                                ) {
                                                    Text(
                                                        text = "Batal", color = Color.Black
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Button(
                                                    onClick = {
                                                        viewModel.Borrow(itemId = item.id)
                                                        viewModel.resetState()
                                                    }, colors = ButtonDefaults.buttonColors(
                                                        containerColor = InfoMain
                                                    ), enabled = item.availableStock > 0
                                                ) {
                                                    Text(
                                                        text = if (item.availableStock > 0) "Pinjam" else "Habis"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
    package io.github.naxx.newinventaris2.ui.Activities.RiwayatScreen.Screen

    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.unit.dp
    import androidx.lifecycle.compose.collectAsStateWithLifecycle
    import androidx.navigation.NavHostController
    import io.github.ComposeKit2.Component.BottomBar.SimpleBottomBar
    import io.github.ComposeKit2.Component.FilterChips.FilterChipItem
    import io.github.ComposeKit2.Component.FilterChips.FilterChips
    import io.github.ComposeKit2.Component.TopBar.HomeTopBar
    import io.github.ComposeKit2.Helper.Fetch.StateHandler
    import io.github.ComposeKit2.Helper.Notify.AppNotifierHost
    import io.github.ComposeKit2.ViewModel.ViewModel.RiwayatViewModel

    @Composable
    fun RiwayatScreen(
        navController: NavHostController,
        viewModel: RiwayatViewModel,
    ) {
        val borrow by viewModel.borrowings.state.collectAsStateWithLifecycle()
        val selectedStatus by viewModel.selectedStatus.collectAsStateWithLifecycle()

        val categories = listOf(
            FilterChipItem("Borrowed"),
            FilterChipItem("Returned")
        )

        Scaffold(
            topBar = { HomeTopBar() },
            bottomBar = {
                SimpleBottomBar(navController)
                AppNotifierHost()
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FilterChips(
                        items = categories,
                        selectedLabel = selectedStatus,
                        onSelect = viewModel::onSelectedStatus
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    StateHandler(
                        state = borrow,
                        emptyMessage = "Tidak Tidak Ada Item Di Pinjam"
                    ) { borrowList->
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(borrowList) { item ->
                                CardRiwayat(item, onReturnClick = viewModel::KembalikanBarang)
                            }
                        }
                    }
                }
            }
        }
    }
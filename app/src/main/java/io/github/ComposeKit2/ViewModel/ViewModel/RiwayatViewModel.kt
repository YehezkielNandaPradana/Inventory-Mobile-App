package io.github.ComposeKit2.ViewModel.ViewModel

import ComposeKit.Helper.Api.ApiClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ComposeKit2.Helper.Notify.AppNotifier
import io.github.ComposeKit2.Helper.PopUp.DialogHelper
import io.github.ComposeKit2.Model.Borrowings
import io.github.ComposeKit2.Model.ReturnResponse
import io.github.ComposeKit2.ViewModel.ResourceState.ResourceState
import io.github.ComposeKit2.ViewModel.ResourceState.execute
import io.github.ComposeKit2.ViewModel.Search.FilterHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RiwayatViewModel : ViewModel() {

    private var allBorrowings: List<Borrowings> = emptyList()
    val borrowings = ResourceState<List<Borrowings>>()

    private val _selectedStatus = MutableStateFlow<String?>(null)
    val selectedStatus: StateFlow<String?> = _selectedStatus

    val returnItem = ResourceState<ReturnResponse>()

    init {
        loadData()
    }

    private fun applyFilter() {
        val filtered = FilterHelper.filter(
            items = allBorrowings,
            selected = selectedStatus.value,
            filterBy = { it.status }
        )
        borrowings.success(filtered)
    }

    fun onSelectedStatus(status: String?) {
        _selectedStatus.value = status
        applyFilter()
    }

    fun loadData() {
        viewModelScope.launch {
            borrowings.execute(
                request = {
                    ApiClient.service.getBorrowings()
                },
                onSuccess = {
                    allBorrowings = it
                    applyFilter()
                }
            )
        }
    }

    fun KembalikanBarang(itemId: Int) {
        viewModelScope.launch {
            returnItem.execute(
                request = {
                    ApiClient.service.returnItem(itemId)
                },
                onSuccess = {
                    AppNotifier.success("Berhasil Mengembalikan Item")
                    loadData()
                    DialogHelper.close()
                },
                onError = AppNotifier::error
            )
        }
    }
}
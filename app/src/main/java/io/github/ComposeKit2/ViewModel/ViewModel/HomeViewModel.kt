package io.github.ComposeKit2.ViewModel.ViewModel

import ComposeKit.Helper.Api.ApiClient
import ComposeKit.Helper.Api.State
import ComposeKit.Helper.Validation.ValidationState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ComposeKit2.Helper.Notify.AppNotifier
import io.github.ComposeKit2.Helper.PopUp.DialogHelper
import io.github.ComposeKit2.Model.AddItemRequest
import io.github.ComposeKit2.Model.AddItemResponse
import io.github.ComposeKit2.Model.BorrowRequest
import io.github.ComposeKit2.Model.Borrowings
import io.github.ComposeKit2.Model.Categories
import io.github.ComposeKit2.Model.Items
import io.github.ComposeKit2.ViewModel.ResourceState.ResourceState
import io.github.ComposeKit2.ViewModel.ResourceState.execute
import io.github.ComposeKit2.ViewModel.Search.FilterHelper
import io.github.ComposeKit2.ViewModel.Search.SearchHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var allItem: List<Items> = emptyList()
    val items = ResourceState<List<Items>>()
    val categories = ResourceState<List<Categories>>()
    val borrow = ResourceState<Borrowings>()
    val addItemState = ResourceState<AddItemResponse>()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val email = State("")
    val name = State("")
    val stock = State("")
    val idCategory = State(0)

    var emailValidation by mutableStateOf(ValidationState())
        private set

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    init {
        loadData()
        loadCategories()
    }

    fun onEmailChange(v: String) {
        email.value = v
    }

    fun onSearchChange(query: String) {
        _searchQuery.value = query
        applyFilter()
    }

    fun onSelectedCategory(kategori: String?) {
        _selectedCategory.value = kategori
        applyFilter()
    }

    private fun applyFilter() {

        val searched = SearchHelper.filter(
            items = allItem,
            query = searchQuery.value,
            selector = { it.name }
        )

        val filtered = FilterHelper.filter(
            items = searched,
            selected = selectedCategory.value,
            filterBy = { it.categoryName }
        )

        items.success(filtered)
    }


    fun loadData() {
        viewModelScope.launch {
            items.execute(
                request = {
                    ApiClient.service.getItem()
                },
                onSuccess = {
                    allItem = it
                    applyFilter()
                }
            )
        }
    }

    fun loadCategories() {
        viewModelScope.launch {
            categories.execute(
                request = {
                    ApiClient.service.getCategories()
                },
            )
        }
    }

    fun borrow(itemId: Int) {
        if(emailValidation.isError){
            AppNotifier.error("Email Tidak Valid")
            return
        }
        viewModelScope.launch {
            borrow.execute(
                request = {
                    ApiClient.service.postBorrow(
                        BorrowRequest(
                            itemId,
                            email.value
                        )
                    )
                },
                onSuccess = {
                    AppNotifier.success("Berhasil Pinjam Item")
                    loadData()
                    DialogHelper.close()
                },
                onError = {
                    AppNotifier.error(it)
                }
            )
        }
    }

    fun addItem() {

        val stockValue = stock.value.toIntOrNull()

        if (name.value.isBlank()) {
            AppNotifier.error("Nama Tidak Boleh Kosong")
            return
        }

        if (idCategory.value == 0) {
            AppNotifier.error("Kategori Belum Di Pilih!")
            return
        }

        if (stockValue == null || stockValue < 0) {
            AppNotifier.error("Stock Tidak Valid")
            return
        }

        viewModelScope.launch {
            addItemState.execute(
                request = {
                    ApiClient.service.addItem(
                        AddItemRequest(
                            idCategory = idCategory.value,
                            name = name.value,
                            totalStock = stockValue
                        )
                    )
                },
                onSuccess = {
                    AppNotifier.success("Berhasil Tambah Item")
                    ResetState()
                    loadData()
                    DialogHelper.close()
                },
                onError = AppNotifier::error
            )
        }
    }

    fun ResetState() {
        email.value = ""
        name.value = ""
        stock.value = ""
        idCategory.value = 0
    }
}


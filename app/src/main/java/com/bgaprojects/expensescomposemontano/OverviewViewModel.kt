package com.bgaprojects.expensescomposemontano

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgaprojects.expensescomposemontano.model.Transaction
import com.bgaprojects.expensescomposemontano.repository.DummyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class OverviewViewModel(
    private val repository: DummyRepository = DummyRepository
) : ViewModel() {

    private val filter = MutableStateFlow<String?>(null)
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {

        }
    }

    fun addTransaction(transaction: Transaction) {
        repository.add(transaction)
        updateUiState()
    }

    fun clearTransactions() {
        repository.clearTransactions()
        updateUiState()
    }

    fun updateTransaction(transaction: Transaction) {
        repository.updateTransaction(transaction)
        updateUiState()
    }

    fun deleteTransaction(uuid: String) {
        repository.deleteTransaction(uuid)
        updateUiState()
    }

    fun findTransaction(uuid: String) = repository.findTransaction(uuid)

    fun filterByCategory(category: String) {
        filter.value = category
        updateUiState()
    }

    fun clearFilter() {
        filter.value = null
        updateUiState()
    }

    private fun updateUiState() {
        val transactionListSaved = repository.transactions
        val transactions = if (filter.value != null) {
            transactionListSaved.filter { it.category == filter.value }
        } else {
            transactionListSaved
        }

        _uiState.value = UiState(
            transactions = transactions,
            total = transactionListSaved.sumOf { it.value }
        )
    }

    data class UiState(
        val userName: String = "",
        val transactions: List<Transaction> = emptyList(),
        val total: BigDecimal = transactions.sumOf { it.value }
    )
}
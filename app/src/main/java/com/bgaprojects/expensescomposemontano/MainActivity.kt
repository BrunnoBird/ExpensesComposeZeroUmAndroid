package com.bgaprojects.expensescomposemontano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bgaprojects.expensescomposemontano.ui.theme.ExpensesComposeMontanoTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun add(transaction: String) {
        val transactions = _uiState.value.transactions.toMutableList()
        transactions.add(transaction)
        _uiState.value = UiState(transactions = transactions)
    }

    data class UiState(
        val transactions: List<String> = emptyList()
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpensesComposeMontanoTheme {
                Column {
                    Welcome()
                    Transactions()
                }
            }
        }
    }
}

@Composable
fun Welcome() {
    Row(
        modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Welcome back, \n Brunno",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Clear",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun Transactions(viewModel: MyViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            items(uiState.transactions.size) { index ->
                Transaction(uiState.transactions[index])
            }
        }
        Button(
            onClick = { viewModel.add("New Transaction") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add new Transaction")
        }
    }
}

@Composable
fun Transaction(transaction: String) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = transaction,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}
package com.bgaprojects.expensescomposemontano.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.ShoppingCart
import com.bgaprojects.expensescomposemontano.model.Transaction
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale
import java.util.Random

fun BigDecimal.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return numberFormat.format(this)
}

val categories = listOf(
    "Food" to Icons.Default.Fastfood,
    "Transport" to Icons.Default.DirectionsCar,
    "Shopping" to Icons.Default.ShoppingCart,
    "Health" to Icons.Default.LocalHospital,
    "Entertainment" to Icons.Default.Movie,
    "Utilities" to Icons.Default.Lightbulb
)

fun randomTransaction() = Transaction(
    category = categories.shuffled().first().first,
    value = BigDecimal.valueOf(Random().nextDouble() / Random().nextDouble())

)
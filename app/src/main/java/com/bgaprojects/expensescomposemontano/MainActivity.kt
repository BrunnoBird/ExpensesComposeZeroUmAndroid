package com.bgaprojects.expensescomposemontano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.bgaprojects.expensescomposemontano.ui.OverViewScreen
import com.bgaprojects.expensescomposemontano.ui.theme.ExpensesComposeMontanoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpensesComposeMontanoTheme {
                Column {
                    OverViewScreen()
                }
            }
        }
    }
}
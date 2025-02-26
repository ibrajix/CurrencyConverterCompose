package com.ibrajix.currencyconvertercompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrajix.currencyconvertercompose.components.CurrencyInputSection
import com.ibrajix.currencyconvertercompose.ui.theme.LightBlue
import com.ibrajix.currencyconvertercompose.view_model.CurrencyViewModel


@Composable
fun CurrencyConverterScreen(
    viewModel: CurrencyViewModel = hiltViewModel()
) {
    val fromCurrency by viewModel.fromCurrency.collectAsState()
    val toCurrency by viewModel.toCurrency.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val result by viewModel.result.collectAsState()
    val exchangeRate by viewModel.exchangeRate.collectAsState()
    val historyTimeframe by viewModel.historyTimeframe.collectAsState()
    val rateHistory by viewModel.rateHistory.collectAsState()
    val conversionState by viewModel.conversionState.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color(0xFF0FCCB5)
                )
                Text(
                    text = "Sign up",
                    color = Color(0xFF0FCCB5),
                    fontWeight = FontWeight.Medium
                )
            }


            Text(
                text = "Currency\nCalculator.",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.padding(bottom = 24.dp)
            )


            CurrencyInputSection(
                fromCurrency = fromCurrency,
                toCurrency = toCurrency,
                amount = amount,
                result = result?.toString() ?: "",
                onAmountChange = { viewModel.updateAmount(it) },
                onFromCurrencyChange = { viewModel.updateFromCurrency(it) },
                onToCurrencyChange = { viewModel.updateToCurrency(it) }
            )


            Button(
                onClick = { viewModel.convertCurrency() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FCCB5)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Convert",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            
        }
    }
}
package com.ibrajix.currencyconvertercompose.view

import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrajix.currencyconvertercompose.components.CurrencyInputSection
import com.ibrajix.currencyconvertercompose.ui.theme.MainBlue
import com.ibrajix.currencyconvertercompose.ui.theme.MainGreen
import com.ibrajix.currencyconvertercompose.ui.theme.White
import com.ibrajix.currencyconvertercompose.view_model.ConversionState
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
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(conversionState) {
        if (conversionState is ConversionState.Error) {
            Toast.makeText(
                context,
                (conversionState as ConversionState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
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
                    tint = MainGreen
                )
                Text(
                    text = "Sign up",
                    color = MainGreen,
                    fontWeight = FontWeight.W700
                )
            }

            Spacer(modifier = Modifier.size(20.dp))


            Text(
                text = buildAnnotatedString {
                    pushStyle(SpanStyle(fontWeight = FontWeight.W900))
                    append("Currency\n")
                    append("Calculator")
                    pushStyle(SpanStyle(color = MainGreen))
                    append(".")
                    pop()
                },
                fontSize = 28.sp,
                fontWeight = FontWeight.W900,
                lineHeight = 30.sp,
                color = MainBlue,
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

            Spacer(modifier = Modifier.size(24.dp))

            Button(
                onClick = {
                    keyboardController?.hide()
                    viewModel.convertCurrency()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainGreen
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                if (conversionState is ConversionState.Loading) {
                    CircularProgressIndicator(
                        color = White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = "Convert",
                        color = White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600
                    )
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MainBlue), // Match background color
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No chart to display yet :))",
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

//                ExchangeRateChart()
            }

        }
    }
}
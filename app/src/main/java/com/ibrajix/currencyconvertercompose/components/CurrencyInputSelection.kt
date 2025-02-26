package com.ibrajix.currencyconvertercompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrajix.currencyconvertercompose.ui.theme.LightGray

@Composable
fun CurrencyInputSection(
    fromCurrency: String,
    toCurrency: String,
    amount: String,
    result: String,
    onAmountChange: (String) -> Unit,
    onFromCurrencyChange: (String) -> Unit,
    onToCurrencyChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // From Currency
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = amount,
                onValueChange = { onAmountChange(it) },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = fromCurrency,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.LightGray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = result.ifEmpty { "0.00" },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = result.ifEmpty { "0" }.takeLast(3),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightGray
                )
            }
            Text(
                text = toCurrency,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.LightGray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CurrencySelector(
                currencyCode = fromCurrency,
                onClick = { /* Show currency picker */ }
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Swap currencies",
                modifier = Modifier
                    .size(24.dp)
                    .rotate(90f),
                tint = Color.Gray
            )

            CurrencySelector(
                currencyCode = toCurrency,
                onClick = { /* Show currency picker */ }
            )
        }
    }
}

@Composable
fun CurrencySelector(
    currencyCode: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currencyCode.first().toString(),
                color = Color.White,
                fontSize = 12.sp
            )
        }

        Text(
            text = currencyCode,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Select Currency",
            tint = Color.Gray
        )
    }
}
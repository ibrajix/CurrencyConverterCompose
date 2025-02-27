package com.ibrajix.currencyconvertercompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arpitkatiyarprojects.countrypicker.CountryPicker
import com.arpitkatiyarprojects.countrypicker.enums.CountryListDisplayType
import com.arpitkatiyarprojects.countrypicker.models.CountriesListDialogDisplayProperties
import com.arpitkatiyarprojects.countrypicker.models.CountriesListDialogProperties
import com.arpitkatiyarprojects.countrypicker.models.CountryPickerDialogTextStyles
import com.arpitkatiyarprojects.countrypicker.models.FlagDimensions
import com.arpitkatiyarprojects.countrypicker.models.SelectedCountryDisplayProperties
import com.arpitkatiyarprojects.countrypicker.models.SelectedCountryProperties
import com.arpitkatiyarprojects.countrypicker.models.SelectedCountryTextStyles
import com.ibrajix.currencyconvertercompose.R
import com.ibrajix.currencyconvertercompose.ui.theme.LightGrey2
import com.ibrajix.currencyconvertercompose.utils.CountryCurrencyMapper
import java.util.Locale

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
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGrey2, shape = RoundedCornerShape(5.dp))
                .padding(horizontal = 9.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = amount,
                onValueChange = { onAmountChange(it) },
                modifier = Modifier
                    .weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = fromCurrency.uppercase(Locale.getDefault()),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Result display section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGrey2, shape = RoundedCornerShape(5.dp))
                .padding(horizontal = 9.dp, vertical = 12.dp),
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
            }
            Text(
                text = toCurrency.uppercase(Locale.getDefault()),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            var fromCountryCode by remember {
                mutableStateOf(
                    CountryCurrencyMapper.getCountryCode(
                        fromCurrency
                    )
                )
            }
            var toCountryCode by remember {
                mutableStateOf(
                    CountryCurrencyMapper.getCountryCode(
                        toCurrency
                    )
                )
            }

            CountryCurrencySelector(
                onCurrencySelected = onFromCurrencyChange,
                countryCode = fromCountryCode,
                onCountryCodeChange = { fromCountryCode = it }

            )


            Image(
                painter = painterResource(id = R.drawable.convert),
                contentDescription = "Swap Icon",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {

                        val tempCurrency = fromCurrency
                        onFromCurrencyChange(toCurrency)
                        onToCurrencyChange(tempCurrency)

                        val tempCountryCode = fromCountryCode
                        fromCountryCode = toCountryCode
                        toCountryCode = tempCountryCode
                    }
            )


            CountryCurrencySelector(
                onCurrencySelected = onToCurrencyChange,
                countryCode = toCountryCode,
                onCountryCodeChange = { toCountryCode = it }
            )
        }
    }
}

@Composable
fun CountryCurrencySelector(
    onCurrencySelected: (String) -> Unit,
    countryCode: String,
    onCountryCodeChange: (String) -> Unit
) {


    val selectedCountryDisplayProperties = SelectedCountryDisplayProperties(
        properties = SelectedCountryProperties(
            showCountryFlag = true,
            showCountryPhoneCode = false,
            showCountryName = false,
            showCountryCode = false
        ),
        flagDimensions = FlagDimensions(
            width = 24.dp,
            height = 16.dp
        ),
        textStyles = SelectedCountryTextStyles(
            countryCodeTextStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
    )


    val countriesListDialogDisplayProperties = CountriesListDialogDisplayProperties(
        properties = CountriesListDialogProperties(
            showCountryCode = true
        ),
        textStyles = CountryPickerDialogTextStyles(
            countryNameTextStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            titleTextStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    )


    Row(
        modifier = Modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(5.dp))
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
       
        CountryPicker(
            modifier = Modifier,
            defaultPaddingValues = PaddingValues(0.dp, 0.dp),
            selectedCountryDisplayProperties = selectedCountryDisplayProperties,
            countriesListDialogDisplayProperties = countriesListDialogDisplayProperties,
            defaultCountryCode = countryCode,
            countryListDisplayType = CountryListDisplayType.Dialog,
            onCountrySelected = { countryDetails ->

                val currencyCode = CountryCurrencyMapper.getCurrencyCode(countryDetails.countryCode)
                onCurrencySelected(currencyCode)
                onCountryCodeChange(countryDetails.countryCode)
            }
        )


    }
}
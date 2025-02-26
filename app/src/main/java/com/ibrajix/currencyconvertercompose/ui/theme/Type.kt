package com.ibrajix.currencyconvertercompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ibrajix.currencyconvertercompose.R

private val Onest = FontFamily(
    Font(R.font.onest_regular, FontWeight.W200),
    Font(R.font.onest_regular, FontWeight.W300),
    Font(R.font.onest_regular, FontWeight.W400),
    Font(R.font.onest_medium, FontWeight.W500),
    Font(R.font.onest_medium, FontWeight.W600),
    Font(R.font.onest_semi_bold, FontWeight.W700),
    Font(R.font.onest_semi_bold, FontWeight.W800),
    Font(R.font.onest_bold, FontWeight.W900),
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Onest,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)
package com.ibrajix.currencyconvertercompose.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun ExchangeRateChart() {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                setTouchEnabled(true)
                setPinchZoom(true)
                setBackgroundColor(android.graphics.Color.BLUE)
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    granularity = 1f
                    textColor = android.graphics.Color.WHITE
                    setDrawGridLines(false)
                }
                axisLeft.apply {
                    textColor = android.graphics.Color.WHITE
                    setDrawGridLines(false)
                }
                axisRight.isEnabled = false
                legend.isEnabled = false
            }
        },
        update = { chart ->
            val entries = listOf(
                Entry(1f, 4000f),
                Entry(7f, 4200f),
                Entry(15f, 4242f), // Highlighted point
                Entry(23f, 4150f),
                Entry(30f, 4300f)
            )

            val dataSet = LineDataSet(entries, "Exchange Rate").apply {
                color = android.graphics.Color.WHITE
                valueTextColor = android.graphics.Color.WHITE
                setCircleColor(android.graphics.Color.GREEN)
                circleRadius = 6f
                setDrawValues(false)
                setDrawFilled(true)
                fillColor = android.graphics.Color.BLUE
                mode = LineDataSet.Mode.CUBIC_BEZIER
            }

            chart.data = LineData(dataSet)
            chart.invalidate()
        }
    )
}


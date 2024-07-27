package ru.auskov.jetpackc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import ru.auskov.jetpackc.ui.theme.JetpackCTheme
import kotlin.random.Random

const val steps = 10

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pointsList = getPointsList()
            val xAxisData = AxisData.Builder()
                .axisStepSize(100.dp)
                .backgroundColor(Color.Transparent)
                .steps(pointsList.size - 1)
                .labelData { i -> i.toString() + "d" }
                .labelAndAxisLinePadding(15.dp)
                .build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .backgroundColor(Color.Transparent)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    val min = getMinValue(pointsList)
                    val max = getMaxValue(pointsList)
                    val yScale = (max - min) / steps.toFloat()
                    String.format("%.1f", ((i * yScale) + min))
                }.build()

            JetpackCTheme {
                val lineChartData = LineChartData(
                    linePlotData = LinePlotData(
                        lines = listOf(
                            Line(
                                dataPoints = pointsList,
                                LineStyle(color = Color.Green),
                                IntersectionPoint(color = Color.Blue, radius = 3.dp),
                                SelectionHighlightPoint(),
                                ShadowUnderLine(),
                                SelectionHighlightPopUp()
                            )
                        ),
                    ),
                    xAxisData = xAxisData,
                    yAxisData = yAxisData,
                    gridLines = GridLines(),
                    backgroundColor = Color.White
                )

                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    lineChartData = lineChartData
                )
            }
        }
    }
}

fun getMinValue(list: List<Point>): Float {
    var min = 100F
    list.forEach{
        if (min > it.y) min = it.y
    }

    return min
}

fun getMaxValue(list: List<Point>): Float {
    var max = 0F
    list.forEach{
        if (max < it.y) max = it.y
    }

    return max
}

fun getPointsList(): List<Point> {
    val list = ArrayList<Point>()

    for (i in 0..31) {
        list.add(
            Point(
                i.toFloat(),
                Random.nextInt(50, 100).toFloat()
            )
        )
    }

    return list
}
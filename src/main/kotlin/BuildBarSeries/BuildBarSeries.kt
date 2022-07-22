package BuildBarSeries

import org.ta4j.core.*
import org.ta4j.core.num.DoubleNum
import ta4jexamples.barSeries.BuildBarSeries
import java.time.Duration
import java.time.ZonedDateTime
import java.util.*

class BuildBarSeries {

    companion object {

        fun buildAndAddBarsFromList():BarSeries {

            val endTime: ZonedDateTime = ZonedDateTime.now()
            val b1 = barBuilderFromString()?.timePeriod(Duration.ofDays(1))?.endTime(endTime)?.openPrice("105.42")
                ?.highPrice("112.99")?.lowPrice("104.01")?.closePrice("111.42")?.volume("1337")?.build()

            var bars: MutableList<BaseBar?> = Arrays.asList(b1)

            return BaseBarSeriesBuilder().withName("bitcoin").withNumTypeOf(DoubleNum::valueOf).withMaxBarCount(5)
                .withBars(bars as List<Bar>?).build();
        }

        fun barBuilderFromString(): ConvertibleBaseBarBuilder<String>? {
            return BaseBar.builder(
                { i: String? -> DoubleNum.valueOf(i) },
                String::class.java
            )
        }
    }



}

fun main() {

    var a = BuildBarSeries?.buildAndAddBarsFromList()

    println("a: " + a.getBar(0).closePrice.name)

    BaseBarSeriesBuilder.setDefaultFunction { i: Number? ->
        DoubleNum.valueOf(i)
    }



}
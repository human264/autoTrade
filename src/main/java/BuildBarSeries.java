import org.ta4j.core.*;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.DoubleNum;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class BuildBarSeries {


    /**
     * Calls different functions that shows how a BaseBarSeries could be created and
     * how Bars could be added
     *
     * @param args command line arguments (ignored)
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        BarSeries a = buildAndAddData();
        System.out.println("a: " + a.getBar(0).getClosePrice().getName());
        BaseBarSeriesBuilder.setDefaultFunction(DoubleNum::valueOf);
        a = buildAndAddData();
        System.out.println("a: " + a.getBar(0).getClosePrice().getName());
        BarSeries b = buildWithDouble();
        System.out.println("b: " + b.getBar(0).getClosePrice().getName());

        BarSeries c = buildWithBigDecimal();

        System.out.println("c: " + c.getBar(0).getClosePrice().getName());
        BarSeries d = buildManually();

        System.out.println("d: " + d.getBar(0).getClosePrice().getName());
        BarSeries e = buildManuallyDoubleNum();

        System.out.println("e: " + e.getBar(0).getClosePrice().getName());

        BarSeries f = buildManuallyAndAddBarManually();
        System.out.println("f: " + f.getBar(0).getClosePrice().getName());

        BarSeries g = buildAndAddBarsFromList();
        System.out.println("g: " + g.getBar(0).getClosePrice().getName());
    }

    private static BarSeries buildAndAddData() {
        BarSeries series = new BaseBarSeriesBuilder().withName("mySeries").build();

        ZonedDateTime endTime = ZonedDateTime.now();
        series.addBar(endTime, 105.42, 112.99, 104.01, 111.42, 1337);
        series.addBar(endTime.plusDays(1), 111.43, 112.83, 107.77, 107.99, 1234);
        series.addBar(endTime.plusDays(2), 107.90, 117.50, 107.90, 115.42, 4242);
        // ...
        return series;
    }

    private static BarSeries buildWithDouble() {
        BarSeries series = new BaseBarSeriesBuilder().withName("mySeries").withNumTypeOf(DoubleNum.class).build();

        ZonedDateTime endTime = ZonedDateTime.now();
        series.addBar(endTime, 105.42, 112.99, 104.01, 111.42, 1337);
        series.addBar(endTime.plusDays(1), 111.43, 112.83, 107.77, 107.99, 1234);
        series.addBar(endTime.plusDays(2), 107.90, 117.50, 107.90, 115.42, 4242);
        // ...

        return series;
    }

    private static BarSeries buildWithBigDecimal() {
        BarSeries series = new BaseBarSeriesBuilder().withName("mySeries").withNumTypeOf(DecimalNum.class).build();

        ZonedDateTime endTime = ZonedDateTime.now();
        series.addBar(endTime, 105.42, 112.99, 104.01, 111.42, 1337);
        series.addBar(endTime.plusDays(1), 111.43, 112.83, 107.77, 107.99, 1234);
        series.addBar(endTime.plusDays(2), 107.90, 117.50, 107.90, 115.42, 4242);
        // ...

        return series;
    }

    private static BarSeries buildManually() {
        BarSeries series = new BaseBarSeries("mySeries"); // uses BigDecimalNum

        ZonedDateTime endTime = ZonedDateTime.now();
        series.addBar(endTime, 105.42, 112.99, 104.01, 111.42, 1337);
        series.addBar(endTime.plusDays(1), 111.43, 112.83, 107.77, 107.99, 1234);
        series.addBar(endTime.plusDays(2), 107.90, 117.50, 107.90, 115.42, 4242);
        // ...

        return series;
    }

    private static BarSeries buildManuallyDoubleNum() {
        BarSeries series = new BaseBarSeries("mySeries", DoubleNum::valueOf); // uses DoubleNum
        ZonedDateTime endTime = ZonedDateTime.now();
        series.addBar(endTime, 105.42, 112.99, 104.01, 111.42, 1337);
        series.addBar(endTime.plusDays(1), 111.43, 112.83, 107.77, 107.99, 1234);
        series.addBar(endTime.plusDays(2), 107.90, 117.50, 107.90, 115.42, 4242);
        // ...

        return series;
    }

    private static BarSeries buildManuallyAndAddBarManually() {
        BarSeries series = new BaseBarSeries("mySeries", DoubleNum::valueOf); // uses DoubleNum

        // create bars and add them to the series. The bars must have the same Num type
        // as the series
        ZonedDateTime endTime = ZonedDateTime.now();
        Bar b1 = BaseBar.builder(DoubleNum::valueOf, Double.class).timePeriod(Duration.ofDays(1)).endTime(endTime)
                .openPrice(105.42).highPrice(112.99).lowPrice(104.01).closePrice(111.42).volume(1337.0).build();
        Bar b2 = BaseBar.builder(DoubleNum::valueOf, Double.class).timePeriod(Duration.ofDays(1))
                .endTime(endTime.plusDays(1)).openPrice(111.43).highPrice(112.83).lowPrice(107.77).closePrice(107.99)
                .volume(1234.0).build();
        Bar b3 = BaseBar.builder(DoubleNum::valueOf, Double.class).timePeriod(Duration.ofDays(1))
                .endTime(endTime.plusDays(2)).openPrice(107.90).highPrice(117.50).lowPrice(107.90).closePrice(115.42)
                .volume(4242.0).build();
        // ...

        series.addBar(b1);
        series.addBar(b2);
        series.addBar(b3);

        return series;
    }

    private static BarSeries buildAndAddBarsFromList() {
        // Store Bars in a list and add them later. The bars must have the same Num type
        // as the series
        ZonedDateTime endTime = ZonedDateTime.now();
        Bar b1 = barBuilderFromString().timePeriod(Duration.ofDays(1)).endTime(endTime).openPrice("105.42")
                .highPrice("112.99").lowPrice("104.01").closePrice("111.42").volume("1337").build();
        Bar b2 = barBuilderFromString().timePeriod(Duration.ofDays(1)).endTime(endTime.plusDays(1)).openPrice("111.43")
                .highPrice("112.83").lowPrice("107.77").closePrice("107.99").volume("1234").build();
        Bar b3 = barBuilderFromString().timePeriod(Duration.ofDays(1)).endTime(endTime.plusDays(2)).openPrice("107.90")
                .highPrice("117.50").lowPrice("107.90").closePrice("115.42").volume("4242").build();
        List<Bar> bars = Arrays.asList(b1, b2, b3);

        return new BaseBarSeriesBuilder().withName("mySeries").withNumTypeOf(DoubleNum::valueOf).withMaxBarCount(5)
                .withBars(bars).build();
    }

    private static ConvertibleBaseBarBuilder<String> barBuilderFromString() {
        return BaseBar.builder(DoubleNum::valueOf, String.class);
    }
}

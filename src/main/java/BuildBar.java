import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.num.Num;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;

public class BuildBar implements BarSeries {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Bar getBar(int i) {
        return null;
    }

    @Override
    public int getBarCount() {
        return 0;
    }

    @Override
    public List<Bar> getBarData() {
        return null;
    }

    @Override
    public int getBeginIndex() {
        return 0;
    }

    @Override
    public int getEndIndex() {
        return 0;
    }

    @Override
    public int getMaximumBarCount() {
        return 0;
    }

    @Override
    public void setMaximumBarCount(int maximumBarCount) {

    }

    @Override
    public int getRemovedBarsCount() {
        return 0;
    }

    @Override
    public void addBar(Bar bar, boolean replace) {

    }

    @Override
    public void addBar(Duration timePeriod, ZonedDateTime endTime) {

    }

    @Override
    public void addBar(ZonedDateTime endTime, Num openPrice, Num highPrice, Num lowPrice, Num closePrice, Num volume, Num amount) {

    }

    @Override
    public void addBar(Duration timePeriod, ZonedDateTime endTime, Num openPrice, Num highPrice, Num lowPrice, Num closePrice, Num volume) {

    }

    @Override
    public void addBar(Duration timePeriod, ZonedDateTime endTime, Num openPrice, Num highPrice, Num lowPrice, Num closePrice, Num volume, Num amount) {

    }

    @Override
    public void addTrade(Num tradeVolume, Num tradePrice) {

    }

    @Override
    public void addPrice(Num price) {

    }

    @Override
    public BarSeries getSubSeries(int startIndex, int endIndex) {
        return null;
    }

    @Override
    public Num numOf(Number number) {
        return null;
    }

    @Override
    public Function<Number, Num> function() {
        return null;
    }
}

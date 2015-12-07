package parser;

import entity.CommodityItem;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class CommodityParser extends Parser<CommodityItem> {
    private final Pattern PATTERN =compile("^(\\w+):(\\d+)$");

    @Override
    protected CommodityItem parseLine(final String line) {
        String barcode = line.split(":")[0];
        double price = Double.parseDouble(line.split(":")[1]);
        return new CommodityItem(barcode, price);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

}

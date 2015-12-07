package parser;

import entity.DiscountPromotionItem;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class DiscountPromotionParser extends Parser<DiscountPromotionItem> {
    private final Pattern PATTERN =compile("^(\\w+):(\\d+)$");

    @Override
    protected DiscountPromotionItem parseLine(final String line) {
        String  barcode = line.split(":")[0];
        Integer discount = Integer.parseInt(line.split(":")[1]);
        return new DiscountPromotionItem(barcode, discount);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }
}

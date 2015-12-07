package parser;

import entity.SecondHalfPromotionItem;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class SecondHalfPromotionParser extends Parser<SecondHalfPromotionItem> {
    private final Pattern PATTERN = compile("[A-Za-z0-9]+");

    @Override
    protected SecondHalfPromotionItem parseLine(final String line) {
        return new SecondHalfPromotionItem(line);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }
}

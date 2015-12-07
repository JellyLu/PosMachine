package parser;

import entity.CartItem;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class CartParser extends Parser<CartItem>{
    private final Pattern PATTERN =compile("^(\\w+)-(\\d+)$");

    @Override
    protected CartItem parseLine(final String line) {
        String  barcode  = line.split("-")[0];
        Integer quantity = Integer.parseInt(line.split("-")[1]);
        return new CartItem(barcode, quantity);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }
}

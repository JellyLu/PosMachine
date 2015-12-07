package parser;

import entity.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CartParserTest {
    private CartParser parser;

    @Before
    public void setUp() {
        parser = new CartParser();
    }

    @Test
    public void should_get_empty_goods_given_none() {
        List<CartItem> items = parser.parse(Arrays.<String>asList());
        List<CartItem> expectedItems = asList();
        assertThat(items, is(expectedItems));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_input() {
        parser.parse( asList( "blabla" ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_price() {
        parser.parse(asList( "blabla-d8" ) );
    }

    @Test
    public void should_get_one_commodity() {
        List<CartItem> items = parser.parse(asList("I1-40"));
        assertThat( items.get(0).getBarcode(), is( "I1" ) );
        assertThat( items.get(0).getQuantity(), is( 40 ) );
    }

    @Test
    public void should_get_multiple_commodities() {
        List<CartItem> items = parser.parse(asList("I1-40", "I2-30"));
        assertThat( items.get(0).getBarcode(), is( "I1" ) );
        assertThat( items.get(1).getBarcode(), is( "I2" ) );
        assertThat( items.get(0).getQuantity(), is( 40 ) );
        assertThat( items.get(1).getQuantity(), is( 30 ) );
    }
}

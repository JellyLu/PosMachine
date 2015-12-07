package parser;

import entity.DiscountPromotionItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiscountPromotionTest {
    private DiscountPromotionParser parser;

    @Before
    public void setUp() {
        parser = new DiscountPromotionParser();
    }

    @Test
    public void should_get_empty_goods_given_none() {
        List<DiscountPromotionItem> items = parser.parse(Arrays.<String>asList());
        List<DiscountPromotionItem> expectedItems = asList();
        assertThat(items, is(expectedItems));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_input() {
        parser.parse( asList( "blabla" ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_price() {
        parser.parse(asList( "blabla:d8" ) );
    }

    @Test
    public void should_get_one_commodity() {
        List<DiscountPromotionItem> items = parser.parse(asList("I1:40"));
        assertThat( items.get(0).getBarcode(), is( "I1" ) );
        assertThat( items.get(0).getDiscount(), is( 40 ) );
    }

    @Test
    public void should_get_multiple_commodities() {
        List<DiscountPromotionItem> items = parser.parse(asList("I1:40", "I2:30"));
        assertThat( items.get(0).getBarcode(), is( "I1" ) );
        assertThat( items.get(1).getBarcode(), is( "I2" ) );
        assertThat( items.get(0).getDiscount(), is( 40 ) );
        assertThat( items.get(1).getDiscount(), is( 30 ) );
    }
}

package parser;

import entity.CommodityItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommodityParseTest {
    private CommodityParser parser;

    @Before
    public void setUp() {
        parser = new CommodityParser();
    }

    @Test
    public void should_get_empty_goods_given_none() {
        List<CommodityItem> items = parser.parse(Arrays.<String>asList());
        List<CommodityItem> expectedItems = asList();
        assertThat(items, is(expectedItems));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_input() {
        parser.parse(asList("blabla"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_price() {
        parser.parse(asList("blabla:d8"));
    }

    @Test
    public void should_get_one_commodity() {
        List<CommodityItem> items = parser.parse(asList("I1:40"));
        assertThat(items.get(0).getBarcode(), is("I1"));
        assertEquals(items.get(0).getPrice(), 40, 1e-6);
    }

    @Test
    public void should_get_multiple_commodities() {
        List<CommodityItem> items = parser.parse(asList("I1:40", "I2:30"));
        assertThat(items.get(0).getBarcode(), is("I1"));
        assertThat(items.get(1).getBarcode(), is("I2"));
        assertEquals(items.get(0).getPrice(), 40, 1e-6);
        assertEquals(items.get(1).getPrice(), 30, 1e-6);
    }
}

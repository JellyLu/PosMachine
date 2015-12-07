package parser;

import entity.SecondHalfPromotionItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SecondHalfPromotionParserTest {
    private SecondHalfPromotionParser parser;

    @Before
    public void setUp() {
        parser = new SecondHalfPromotionParser();
    }

    @Test
    public void should_get_empty_goods_given_none() {
        List<SecondHalfPromotionItem> items = parser.parse(Arrays.<String>asList());
        List<SecondHalfPromotionItem> expectedItems = asList();
        assertThat(items, is(expectedItems));
    }

    @Test
    public void should_get_one_commodity() {
        List<SecondHalfPromotionItem> items = parser.parse(asList("I1"));
        assertThat( items.get(0).getBarcode(), is( "I1" ) );
    }

    @Test
    public void should_get_multiple_commodities() {
        List<SecondHalfPromotionItem> items = parser.parse(asList("I1", "I2"));
        assertThat( items.get(0).getBarcode(), is( "I1" ) );
        assertThat( items.get(1).getBarcode(), is( "I2" ) );

    }
}

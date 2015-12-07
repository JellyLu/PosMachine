package posMachine;

import entity.CartItem;
import entity.CommodityItem;
import entity.DiscountPromotionItem;
import entity.SecondHalfPromotionItem;
import org.junit.Before;
import org.junit.Test;
import parser.CommodityParser;
import parser.DiscountPromotionParser;
import parser.SecondHalfPromotionParser;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PosMachineTest {
    private PosMachine posMachine;
    private List<CommodityItem> commodityItems;
    private List<DiscountPromotionItem> discountPromotionItems;
    private List<SecondHalfPromotionItem> secondHalfPromotionItems;

    @Before
    public void setUp(){
        commodityItems           = new CommodityParser().parse( new ShopData().ITEMS_DATA );
        discountPromotionItems   = new DiscountPromotionParser().parse( new ShopData().DISCOUNT_DATA );
        secondHalfPromotionItems = new SecondHalfPromotionParser().parse( new ShopData().SECOND_HALF_DATA );
        posMachine               = new PosMachine( commodityItems, discountPromotionItems, secondHalfPromotionItems );
    }

    @Test
    public void should_return_0_when_shopping_cart_empty() throws Exception{
        List<CartItem> emptyCart = Arrays.asList();
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(0d));
    }

    @Test
    public void should_calculate_total_when_given_1_item_without_promotion() throws Exception{
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000003", 1));
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(50d));
    }

    @Test
    public void should_calculate_total_origin_price_when_given_1_item_with_discount_promotion() throws Exception{
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000003", 1));
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(50d));
    }

    @Test
    public void should_calculate_total_when_given_1_item_with_discount_promotion() throws Exception{
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000005", 1));
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(54d));
    }

    @Test
    public void should_calculate_total_when_given_1_item_with_second_half_promotion() throws Exception{
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000003", 1));
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(50d));
    }

    @Test
    public void should_calculate_total_when_given_1_item_with_discount_and_second_half_promotion() throws Exception{
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000003", 1));
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(50d));
    }

    @Test
    public void should_calculate_total_when_given_multiple_items_without_promotion() throws Exception{
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000001", 3), new CartItem("ITEM000003", 2));
        PosMachine posMachine1 = new PosMachine( commodityItems, null, null );
        double total = posMachine1.calculate(emptyCart);

        assertThat(total, is(220d));
    }

    @Test
    public void should_calculate_total_when_given_multiple_types_item_with_discount_promotion() throws Exception{
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000001", 3), new CartItem("ITEM000003", 2));
        PosMachine posMachine1 = new PosMachine( commodityItems, discountPromotionItems, null );
        double total = posMachine1.calculate(emptyCart);

        assertThat(total, is(190d));
    }

    @Test
    public void should_calculate_total_when_given_multiple_types_item_with_second_half_promotion() throws Exception{
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000001", 3), new CartItem("ITEM000003", 2));
        PosMachine posMachine1 = new PosMachine( commodityItems, null, secondHalfPromotionItems );
        double total = posMachine1.calculate(emptyCart);

        assertThat(total, is(175d));
    }

    @Test
    public void should_calculate_total_when_given_multiple_types_item_with_discount_and_second_half_promotion() throws Exception{
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000001", 3), new CartItem("ITEM000003", 2));
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(150d));
    }
}

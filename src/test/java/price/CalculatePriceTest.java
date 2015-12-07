package price;

import org.junit.Before;
import org.junit.Test;
import promotionStrategy.DiscountPromotion;
import promotionStrategy.SecondHalfPromotion;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatePriceTest {
    private CalculatePrice calculatePrice;

    @Before
    public void setUp(){

        calculatePrice = new CalculatePrice();
    }

    @Test
    public void should_return_300_without_any_promotion() throws Exception{
       assertThat( calculatePrice.getCommodityPrice(  100, 3  ), is( 300.0) );
    }

    @Test
    public void should_return_210_just_discount_promotion() throws Exception{
        DiscountPromotion discountPromotion = new DiscountPromotion( 70 );
        calculatePrice.setPromotions( asList( discountPromotion  ) );
        assertThat( calculatePrice.getCommodityPrice(  100, 3  ), is( 210.0) );
    }

    @Test
    public void should_return_250_just_second_half_promotion() throws Exception{
        SecondHalfPromotion secondHalfPromotion = new SecondHalfPromotion();
        calculatePrice.setPromotions( asList( secondHalfPromotion ) );
        assertThat( calculatePrice.getCommodityPrice(  100, 3  ), is( 250.0) );
    }

    @Test
    public void should_return_175_with_discount_and_second_half_promotion() throws Exception{
        DiscountPromotion discountPromotion = new DiscountPromotion( 70 );
        SecondHalfPromotion secondHalfPromotion = new SecondHalfPromotion();
        calculatePrice.setPromotions( asList( discountPromotion, secondHalfPromotion ) );
        assertThat( calculatePrice.getCommodityPrice(  100, 3  ), is( 175.0) );
    }

    @Test
    public void should_return_175_with_second_half_and_discount_promotion() throws Exception{
        DiscountPromotion discountPromotion = new DiscountPromotion( 70 );
        SecondHalfPromotion secondHalfPromotion = new SecondHalfPromotion();
        calculatePrice.setPromotions( asList( secondHalfPromotion, discountPromotion ) );
        assertThat( calculatePrice.getCommodityPrice(  100, 3  ), is( 175.0) );
    }

}

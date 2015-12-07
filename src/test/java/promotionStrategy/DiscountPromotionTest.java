package promotionStrategy;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DiscountPromotionTest {

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_argument_wrong() throws Exception{
        new DiscountPromotion( -30 );
    }

    @Test
    public void should_return_210_when_70_discount(){
        DiscountPromotion promotion = new DiscountPromotion( 70 );

        assertThat( promotion.getPrice( 100, 3 ), is( 210.0 ) );
    }




}

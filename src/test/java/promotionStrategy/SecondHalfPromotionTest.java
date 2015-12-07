package promotionStrategy;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SecondHalfPromotionTest {

    @Test
    public void should_return_50_when_quantity_is_3(){
        SecondHalfPromotion promotion = new SecondHalfPromotion();

        assertThat( promotion.getPrice( 100, 3 ), is( 50.0 ) );
    }

    @Test
    public void should_return_100_when_quantity_is_4(){
        SecondHalfPromotion promotion = new SecondHalfPromotion();

        assertThat( promotion.getPrice( 100, 4 ), is( 100.0 ) );
    }


}

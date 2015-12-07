package promotionStrategy;

public class DiscountPromotion implements Promotion {
    private final Integer discount;

    public DiscountPromotion(Integer discount) {
        if ( discount < 0 ){
            throw new IllegalArgumentException( "折扣不能小于0" );
        }
        this.discount = discount;
    }

    public double getPrice( double  originPrice, Integer count ){
        return originPrice*discount*count/100;
    }

}

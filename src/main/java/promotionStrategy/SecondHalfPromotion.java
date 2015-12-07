package promotionStrategy;

public class SecondHalfPromotion extends FatherPromotion {

    public double getPrice( double originPrice, Integer quantity ){
        Integer halfQuantity = quantity/2;
        return originPrice*halfQuantity/2;
    }
}

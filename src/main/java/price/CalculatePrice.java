package price;

import promotionStrategy.FatherPromotion;
import promotionStrategy.Promotion;

import java.util.List;

public class CalculatePrice {
    List<FatherPromotion> promotions;

    public CalculatePrice() {
        this.promotions = null;
    }

    public void setPromotions(List<FatherPromotion> promotions) {
        this.promotions = promotions;
    }

    public double getCommodityPrice( double originPrice, Integer quantity )throws Exception{
        double resultPrice = originPrice*quantity;
        if ( promotions == null ){
            return resultPrice;
        }

        for ( Promotion promotion : promotions ) {
             resultPrice -= promotion.getPrice( originPrice, quantity );
             originPrice = resultPrice/quantity;
        }

        return resultPrice;
    }

}

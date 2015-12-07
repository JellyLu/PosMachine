package price;

import promotionStrategy.Promotion;

import java.util.List;

public class CalculatePrice {
    List<Promotion> promotions;

    public CalculatePrice() {
        this.promotions = null;
    }

    public void setPromotions(List<Promotion> promotions) {
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

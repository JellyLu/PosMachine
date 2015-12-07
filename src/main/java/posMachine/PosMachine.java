package posMachine;

import entity.CartItem;
import entity.CommodityItem;
import entity.DiscountPromotionItem;
import entity.SecondHalfPromotionItem;
import price.CalculatePrice;
import promotionStrategy.DiscountPromotion;
import promotionStrategy.FatherPromotion;
import promotionStrategy.SecondHalfPromotion;

import java.util.*;

public class PosMachine {
    private Map<String,List<FatherPromotion>> promotionMap;
    private List<CommodityItem> commodityItems;
    private List<DiscountPromotionItem> discountPromotionItems;
    private List<SecondHalfPromotionItem> secondHalfPromotionItems;

    public PosMachine(List<CommodityItem> commodityItems, List<DiscountPromotionItem> discountPromotionItems, List<SecondHalfPromotionItem> secondHalfPromotionItems) {
        this.commodityItems = commodityItems;
        this.discountPromotionItems = discountPromotionItems;
        this.secondHalfPromotionItems = secondHalfPromotionItems;
        setPromotionMap();
    }

    private void setPromotionMap(){
        promotionMap = new HashMap<String,List<FatherPromotion>>();
        for ( CommodityItem commodity : commodityItems ) {
            List<FatherPromotion> promotions = new ArrayList<FatherPromotion>();
            promotionMap.put( commodity.getBarcode(), promotions );
        }

        if ( discountPromotionItems != null && discountPromotionItems.size() > 0 ) {
            for (DiscountPromotionItem discountPromotionItem : discountPromotionItems) {
                List<FatherPromotion> promotionList = promotionMap.get(discountPromotionItem.getBarcode());
                promotionList.add( new DiscountPromotion( discountPromotionItem.getDiscount() ) );
                promotionMap.put( discountPromotionItem.getBarcode(), promotionList );
            }
        }

        if ( secondHalfPromotionItems != null && secondHalfPromotionItems.size() > 0 ) {
            for (SecondHalfPromotionItem secondHalfPromotionItem : secondHalfPromotionItems) {

                List<FatherPromotion> promotionList = promotionMap.get( secondHalfPromotionItem.getBarcode());
                promotionList.add( new SecondHalfPromotion() );
                promotionMap.put( secondHalfPromotionItem.getBarcode(), promotionList );
            }
        }
    }

    public double calculate(final List<CartItem> cartItems)throws Exception {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += calculateSubtotal(cartItem);
        }
        return total;
    }

    private double calculateSubtotal(final CartItem cartItem) throws Exception{
        String barcode = cartItem.getBarcode();
        CalculatePrice calculatePrice = new CalculatePrice();
        calculatePrice.setPromotions( promotionMap.get( barcode ));

        return calculatePrice.getCommodityPrice( queryItemPrice( barcode ), cartItem.getQuantity() ) ;
    }

    private double queryItemPrice(final String barcode) {
        for ( CommodityItem item : commodityItems) {
            if (item.getBarcode().equals(barcode)) {
                return item.getPrice();
            }
        }

        throw new IllegalArgumentException("unknown item");
    }

}

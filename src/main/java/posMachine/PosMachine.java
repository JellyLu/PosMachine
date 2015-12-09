package posMachine;

import entity.CartItem;
import entity.CommodityItem;
import entity.DiscountPromotionItem;
import entity.SecondHalfPromotionItem;
import price.CalculatePrice;
import promotionStrategy.DiscountPromotion;
import promotionStrategy.Promotion;
import promotionStrategy.SecondHalfPromotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    private Map<String,List<Promotion>> promotionMap;
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
        promotionMap = new HashMap<>();
        for ( CommodityItem commodity : commodityItems ) {
            promotionMap.put( commodity.getBarcode(), new ArrayList<>() );
        }

        addDiscountPromotionToMap();

        addSecondHalfPromotionToMap();
    }

    private void addDiscountPromotionToMap() {
        if ( discountPromotionItems != null && discountPromotionItems.size() > 0 ) {
            for (DiscountPromotionItem discountPromotionItem : discountPromotionItems) {
                List<Promotion> promotionList = promotionMap.get(discountPromotionItem.getBarcode());
                promotionList.add( new DiscountPromotion( discountPromotionItem.getDiscount() ) );
                promotionMap.put( discountPromotionItem.getBarcode(), promotionList );
            }
        }
    }

    private void addSecondHalfPromotionToMap() {
        if ( secondHalfPromotionItems != null && secondHalfPromotionItems.size() > 0 ) {
            for (SecondHalfPromotionItem secondHalfPromotionItem : secondHalfPromotionItems) {

                List<Promotion> promotionList = promotionMap.get( secondHalfPromotionItem.getBarcode());
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

package entity;

public class DiscountPromotionItem {
    private final String  barcode;
    private final Integer discount;

    public DiscountPromotionItem(String barcode, Integer discount) {
        this.barcode = barcode;
        this.discount = discount;
    }

    public String getBarcode() {
        return barcode;
    }

    public Integer getDiscount() {
        return discount;
    }
}

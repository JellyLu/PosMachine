package entity;

public class CommodityItem {
    private final String barcode;
    private final double price;

    public CommodityItem(String barcode, double price) {
        this.barcode = barcode;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }
}

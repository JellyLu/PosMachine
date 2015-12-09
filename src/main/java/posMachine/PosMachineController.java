package posMachine;

import entity.CartItem;
import entity.CommodityItem;
import entity.DiscountPromotionItem;
import entity.SecondHalfPromotionItem;
import input.InputFromText;
import parser.CartParser;
import parser.CommodityParser;
import parser.DiscountPromotionParser;
import parser.SecondHalfPromotionParser;

import java.io.File;
import java.util.List;

public class PosMachineController {

    private List<CommodityItem> commodityItems;
    private List<DiscountPromotionItem> discountPromotionItems;
    private List<SecondHalfPromotionItem> secondHalfPromotionItems;
    private List<CartItem> cartItems;
    private String resourcePath;

    public PosMachineController( String resourcePath ) throws Exception {
        this.resourcePath = resourcePath;
        loadLists();
    }

    private String getPath() throws Exception{
        File directory = new File("");
        String courseFile = directory.getCanonicalPath();
        return courseFile + resourcePath;
    }

    private void loadLists( ) throws Exception{
        InputFromText inputFromText = new InputFromText();
        String path = getPath();

        inputFromText.setTextName( path + "itemlist.txt" );
        commodityItems           = new CommodityParser().parse( inputFromText.getInputList()  );

        inputFromText.setTextName( path + "discount_promotion.txt" );
        discountPromotionItems   = new DiscountPromotionParser().parse( inputFromText.getInputList() );

        inputFromText.setTextName( path + "second_half_price_promotion.txt" );
        secondHalfPromotionItems = new SecondHalfPromotionParser().parse( inputFromText.getInputList() );

        inputFromText.setTextName( path + "cart.txt" );
        cartItems = new CartParser().parse( inputFromText.getInputList() );


    }

    public double consume() throws  Exception{
        PosMachine  posMachine = new PosMachine( commodityItems, discountPromotionItems, secondHalfPromotionItems );
        return posMachine.calculate( cartItems );
    }

}

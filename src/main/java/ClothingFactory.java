import domain.*;

public class ClothingFactory {
    public static Clothing make(ClothingType clothingType, String catalogID, String clothingName) {
        switch (clothingType) {
            case TOP:
                return new TopClothing(catalogID, clothingName);
            case BOTTOM:
                return new BottomClothing(catalogID, clothingName);
            case FOOTWEAR:
                return new FootwearClothing(catalogID, clothingName);
            default:
                throw new IllegalArgumentException("Not a valid clothing type");
        }
    }
}

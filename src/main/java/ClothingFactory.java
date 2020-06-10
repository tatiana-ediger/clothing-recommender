import domain.*;

public class ClothingFactory {
    public static Clothing make(ClothingType clothingType, String clothingName) {
        switch (clothingType) { //TODO: move to factory
            case TOP:
                return new TopClothing(clothingName);
            case BOTTOM:
                return new BottomClothing(clothingName);
            case FOOTWEAR:
                return new FootwearClothing(clothingName);
            default:
                throw new IllegalArgumentException("Not a valid clothing type");
        }
    }
}

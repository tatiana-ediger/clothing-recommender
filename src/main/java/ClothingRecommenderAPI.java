import domain.Attribute;
import domain.Clothing;
import domain.ClothingType;

import java.util.List;

public interface ClothingRecommenderAPI {

    //TODO: Create classes for Bottom, Footwear, other attributes, implement this API, conduct Neo4J queries

    /**
     * The user enters an outfit they like to wear.
     * @param clothes the given outfit
     */
    void enterOutfit(long userID, List<Clothing> clothes);

    /**
     * The user enters the clothing item they like to wear with some of it's attributes.
     * @param clothingName the name of the new clothing item
     * @param attrs the different attributes of the given piece of clothing
     */
    void enterClothingItem(long userID, ClothingType clothingType, String clothingName, List<Attribute> attrs);

    /**
     * The user enters two items of clothing they like to wear together.
     * Must be different types of clothing (ex: top and bottom or bottom and footwear, not bottom and bottom)
     * @param c1 the first item of clothing
     * @param c2 the second item of clothing
     */
    void enterUserPreference(long userID, Clothing c1, Clothing c2);

    List<Outfit> recommendOutfit(long userID);

    /**
     * Given one of their clothing items, recommends an outfit to go with it.
     * @param preferred the given piece of clothing
     * @return the best possible outfit.
     */
    List<Outfit> recommendOutfit(long userID, Clothing preferred);
}

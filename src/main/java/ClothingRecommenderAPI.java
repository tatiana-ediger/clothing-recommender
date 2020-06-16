import domain.*;

import java.util.List;

/**
 * Allows a user to enter in clothing, outfits, or ask the program to recommend an outfit.
 */
public interface ClothingRecommenderAPI {

    //TODO: Conduct Neo4J queries

    /**
     * The user enters an outfit they like to wear.
     *
     * @param clothes the given outfit
     */
    void enterOutfit(long userID, List<Clothing> clothes);

    /**
     * The user enters the clothing item they like to wear with some of it's attributes.
     *
     * @param clothingName the name of the new clothing item
     * @param descriptors  the different descriptors of the given piece of clothing
     * @param groupings    the different groupings of the given piece of clothing
     * @return
     */
    Long addToCatalog(ClothingType clothingType, String clothingName, List<Descriptor> descriptors, List<Grouping> groupings);

    void addToUserCloset(User user, Clothing clothing);

    void aadToUserCloset(Long id, Clothing clothing);

    /**
     * The user enters two items of clothing they like to wear together.
     * Must be different types of clothing (ex: top and bottom or bottom and footwear, not bottom and bottom)
     *
     * @param c1 the first item of clothing
     * @param c2 the second item of clothing
     */
    void enterUserPreference(long userID, Clothing c1, Clothing c2);

    List<Clothing> recommendPurchase(long userID);

    /**
     * Given one of their clothing items, recommends an outfit to go with it.
     *
     * @param preferred the given piece of clothing
     * @return the best possible outfit.
     */
    List<Clothing> recommendPurchaseTogether(long userID, Clothing preferred);
}

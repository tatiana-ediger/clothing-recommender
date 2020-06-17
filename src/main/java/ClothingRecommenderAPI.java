import domain.*;

import java.util.Collection;
import java.util.List;

/**
 * Allows a user to enter in clothing, outfits, or ask the program to recommend an outfit.
 */
public interface ClothingRecommenderAPI {

    /**
     * The user enters the clothing item they like to wear with some of it's attributes.
     *
     * @param clothingName the name of the new clothing item
     * @param descriptors  the different descriptors of the given piece of clothing
     * @param groupings    the different groupings of the given piece of clothing
     */
    void addToCatalog(ClothingType clothingType, String catalogID, String clothingName, List<Descriptor> descriptors,
                      List<Grouping> groupings);

    /**
     * Loads the Clothing item with the corresponding catalogID.
     *
     * @param catalogID the desired clothing item's catalog ID.
     * @return the Clothing item.
     */
    Clothing loadClothingByCatalogID(String catalogID);

    /**
     * @param userID
     * @return
     */
    User loadUserByUsername(String userID);

    Descriptor loadDescriptor(String value);

    Descriptor loadDescriptor(String descriptorType, String value);

    Grouping loadGrouping(String value);

    Grouping loadGrouping(String groupingType, String value);

    /**
     * Adds clothing to a user's closet when you have access to the whole user.
     *
     * @param user     the given User.
     * @param clothing the given Clothing to be added to their closet.
     */
    void addToUserCloset(User user, Clothing clothing);

    /**
     * Adds clothing to a user's closet when you have access to only a user ID.
     *
     * @param username the given User's username.
     * @param clothing the given Clothing to be added to their closet.
     */
    void addToUserCloset(String username, Clothing clothing);

    /**
     * Recommends what a user should by considering other users with similar clothing/tastes.
     *
     * @param userID the id of the User who we are trying to recommend clothing to.
     * @return a list of recommended clothing.
     */
    Collection<Clothing> recommendPurchase(String userID);

    void addUser(String username, String name);

    /**
     * Given a clothing item the user wants to purchase, also recommends other clothing items that share
     * a lot of similar traits.
     *
     * @param userID   the userID of the user we are recommending to.
     * @param selected the article of clothing the user has selected.
     * @return the list of recommended clothing.
     */
    Collection<Clothing> recommendSimilarItems(String userID, Clothing selected);

    /**
     * Given a clothing item the user wants to purchase, also recommends other clothing items that are often
     * purchased at the same time.
     *
     * @param userID   the userID of the user we are recommending to.
     * @param selected the given piece of clothing.
     * @return the list of recommended clothing.
     */
    Collection<Clothing> recommendRelatedItems(String userID, Clothing selected);

    Collection<User> listUsers();

    Collection<Clothing> listClothings();

    Collection<BottomClothing> listBottoms();

    Collection<FootwearClothing> listFootwares();

    Collection<TopClothing> listTops();

    Collection<Descriptor> listDescriptors();

    Collection<ColorDescriptor> listColorDescriptors();

    Collection<FancinessDescriptor> listFancinessDescriptors();

    Collection<BrandDescriptor> listBrandDescriptors();

    Collection<SubtypeDescriptor> listSubtypeDescriptors();

    Collection<Grouping> listGroupings();

    Collection<CollectionGrouping> listCollectionGroupings();

    Collection<SetGrouping> listSetGroupings();

    void addToGrouping(String catalogID, Grouping grouping);
}

import domain.*;
import org.neo4j.ogm.session.Session;

import java.util.List;

/**
 * An implementation of the ClothingRecommenderAPI using Neo4J.
 */
public class ClothingRecommenderAPIIMpl implements ClothingRecommenderAPI {

    private final Neo4jSessionFactory sessionFactory;

    ClothingRecommenderAPIIMpl(Neo4jSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void enterOutfit(long userID, List<Clothing> clothes) {
        //TODO
    }

    @Override
    public void enterClothingItem(long userID, ClothingType clothingType, String clothingName, List<Descriptor> attrs) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Clothing c;
        switch (clothingType) { //TODO: move to factory
            case TOP:
                c = new TopClothing(clothingName);
                break;
            case BOTTOM:
                c = new BottomClothing(clothingName);
                break;
            case FOOTWEAR:
                c = new FootwearClothing(clothingName);
                break;
            default:
                throw new IllegalArgumentException("Not a valid clothing type");
        }
        for (Descriptor attr : attrs) { //TODO: move to addAttributes method
            c.addAttribute(attr);
        }
        session.save(c);
    }

    @Override
    public void enterUserPreference(long userID, Clothing c1, Clothing c2) {
        //TODO
    }

    @Override
    public List<Outfit> recommendOutfit(long userID) {
        return null;
    }

    @Override
    public List<Outfit> recommendOutfit(long userID, Clothing preferred) {
        //TODO : this is where we need to do the big query of finding which clothing items we want to recommend
        // using a ranking system (user preference weighted the largest amount, and then also take into consideration
        // the other edges that might be important
        // EX: wants an outfit for bluejeans -> look for all outfits that have a userPreference edge with bluejeans,
        //                                   -> IF none, look for item of clothing that shares the most ClothingAttrs
        //                                         with bluejeans (ex. blueslacks), and then see if there's a UserPref
        //                                         to Footwear/Tops with this, otherwise look at next clothing item and
        //                                         so on. If we find a UserPref edge for a top but not f
        return null;
    }
}

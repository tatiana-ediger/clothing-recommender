import domain.*;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.util.List;

/**
 * An implementation of the ClothingRecommenderAPI using Neo4J.
 */
public class ClothingRecommenderAPIIMpl implements ClothingRecommenderAPI {
    private Neo4jSessionFactory sessionFactory;

    ClothingRecommenderAPIIMpl(Neo4jSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void enterOutfit(long userID, List<Clothing> clothes) {
        //TODO
    }

    @Override
    public void enterClothingItem(long userID, ClothingType clothingType, String clothingName, List<Attribute> attrs) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Clothing c;
        switch (clothingType) {
            case TOP:
                c = new TopClothing(userID, clothingName);
                break;
            case BOTTOM:
                c = new BottomClothing(userID, clothingName);
                break;
            case FOOTWEAR:
                c = new FootwearClothing(userID, clothingName);
                break;
            default:
                throw new IllegalArgumentException("Not a valid clothing type");
        }
        for (Attribute attr: attrs) {
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
        return null;
    }
}

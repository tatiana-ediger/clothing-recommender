import domain.Clothing;
import domain.ClothingType;
import domain.Descriptor;
import org.neo4j.ogm.session.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void addToCatalog(ClothingType clothingType, String clothingName, List<Descriptor> attrs) {
        Session session = this.sessionFactory.getNeo4jSession();
        Clothing c = ClothingFactory.make(clothingType, clothingName);
        c.addDescriptors(attrs);
        session.save(c);
    }

    @Override
    public void enterUserPreference(long userID, Clothing c1, Clothing c2) {
        //TODO
    }

    @Override
    public List<Clothing> recommendPurchase(long userID) {
        //Rank the number of connections each clothing
        Session session = this.sessionFactory.getNeo4jSession();
        return null; //TODO: implement
    }

    @Override
    public List<Clothing> recommendPurchaseTogether(long userID, Clothing selected) {
        Session session = this.sessionFactory.getNeo4jSession();

        // 1. find the groupings of the selected clothing
        // 2. Find all of the other clothigns in those groupings that are not the same clothingtype
        // 3. Filter by not already in the user's closet
        // TODO: maybe, sort?

        Map<String, Object> params = new HashMap<>();

        Iterable<Clothing> result = session.query(Clothing.class, "MATCH (n:Clothing) RETURN n;", params);
        for (Clothing c : result) {
            System.out.println(c.getName());
        }
        List<Clothing> clothings = new ArrayList<>();
        result.forEach(clothings::add);
        return clothings;
    }
}

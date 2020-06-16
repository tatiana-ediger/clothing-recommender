import domain.*;
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
    public Long addToCatalog(ClothingType clothingType, String clothingName, List<Descriptor> descriptors, List<Grouping> groupings) {
        Session session = this.sessionFactory.getNeo4jSession();
        Clothing c = ClothingFactory.make(clothingType, clothingName);
        c.addDescriptors(descriptors);
        c.addGroupings(groupings);
        session.save(c);
        return c.getId();
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

        Map<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder();

        // 1. find the groupings of the selected clothings
        // 2. Find all of the other clothings in those groupings
        query.append("MATCH (selected:Clothing) WHERE ID(selected) = $cid \n");
        params.put("cid", selected.getId());

        query.append("MATCH (user:User) WHERE ID(user) = $userid \n");
        params.put("userid", userID);

        query.append("MATCH (selected)<-[:CLOTHING_GROUPING]-(groupings:Grouping)");
        query.append("-[:CLOTHING_GROUPING]->(in_groupings:Clothing) \n");
        // 3. Filter by not already in the user's closet
        query.append("WHERE NOT (in_groupings)<-[:Owns]-(user) \n");

        query.append("RETURN in_groupings;");

        // TODO: maybe, sort?


        Iterable<Clothing> result = session.query(Clothing.class, query.toString(), params);
//        Iterable<User> user = session.query(User.class, query.toString(), params);
        for (Clothing c : result) {
            System.out.println(c.getName());
        }
        List<Clothing> clothings = new ArrayList<>();
        result.forEach(clothings::add);

        //2.2 that are not the same clothing type
        clothings = selected.filterOfDifferentType(clothings);
        return clothings;
    }

    @Override
    public void addToUserCloset(User user, Clothing clothing) {
        Session session = this.sessionFactory.getNeo4jSession();
        user.addToCloset(clothing);
        session.save(user);
        user.getId();
    }

    @Override
    public void aadToUserCloset(Long id, Clothing clothing) {
        Session session = this.sessionFactory.getNeo4jSession();
        User user = session.load(User.class, id);
        this.addToUserCloset(user, clothing);
    }
}

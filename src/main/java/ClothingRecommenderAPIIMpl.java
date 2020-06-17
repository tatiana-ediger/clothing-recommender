import domain.*;
import org.neo4j.helpers.collection.Iterators;
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
    public void addToCatalog(ClothingType clothingType, String catalogID, String clothingName, List<Descriptor> descriptors, List<Grouping> groupings) {
        Session session = this.sessionFactory.getNeo4jSession();
        Clothing c = ClothingFactory.make(clothingType, catalogID, clothingName);
        c.addDescriptors(descriptors);
        c.addGroupings(groupings);
        session.save(c);
    }

    @Override
    public Clothing loadClothingByCatalogID(String catalogID) {
        return this.sessionFactory.getNeo4jSession().load(Clothing.class, catalogID);
    }

    @Override
    public User loadUserByUsername(String username) {
        return this.sessionFactory.getNeo4jSession().load(User.class, username);
    }

    @Override
    public List<Clothing> recommendPurchase(long userID) {
        //Rank the number of connections each clothing
        Session session = this.sessionFactory.getNeo4jSession();
        return null; //TODO: implement
    }

    @Override
    public List<Clothing> recommendSimilarItems(String userID, Clothing selected) {
        Session session = this.sessionFactory.getNeo4jSession();

        Map<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder();

        //1. find the descriptors of the selected Clothing
        query.append("MATCH (selected:Clothing { catalogID: '$cid' }) \n");
        params.put("cid", selected.getCatalogId());

        query.append("MATCH (user:User { username: '$userid' }) \n");
        params.put("userid", userID);

        query.append("MATCH (selected)<-[:CLOTHING_DESCRIPTOR]-(descriptors:Descriptor)");
        query.append("-[descriptions:CLOTHING_DESCRIPTOR]->(in_descriptions:Clothing) \n");

        //2. Find all the other clothing that also have these descriptors
        query.append("WHERE NOT (in_descriptions)<-[:Owns]-(user) \n");

        query.append("RETURN in_descriptions;");
        //query.append("ORDER BY count(descriptions);");

        //3. Filter out the clothing items that are already in the user's closet
        Iterable<Clothing> result = session.query(Clothing.class, query.toString(), params);

        return selected.filterOfDifferentType(Iterators.asList(result.iterator()));
    }

    @Override
    public List<Clothing> recommendRelatedItems(String userID, Clothing selected) {
        Session session = this.sessionFactory.getNeo4jSession();

        Map<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder();

        // 1. find the groupings of the selected clothings
        // 2. Find all of the other clothings in those groupings
        query.append("MATCH (selected:Clothing { catalogID: '$cid' }) \n");
        params.put("cid", selected.getCatalogId());

        query.append("MATCH (user:User { username: '$userid' }) \n");
        params.put("userid", userID);

        query.append("MATCH (selected)<-[:CLOTHING_GROUPING]-(groupings:Grouping)");
        query.append("-[:CLOTHING_GROUPING]->(in_groupings:Clothing) \n");
        // 3. Filter by not already in the user's closet
        query.append("WHERE NOT (in_groupings)<-[:Owns]-(user) \n");

        query.append("RETURN in_groupings;");

//        query.append("RETURN selected;");

        Iterable<Clothing> result = session.query(Clothing.class, query.toString(), params);
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
    }

    @Override
    public void addToUserCloset(Long id, Clothing clothing) {
        Session session = this.sessionFactory.getNeo4jSession();
        User user = session.load(User.class, id);
        this.addToUserCloset(user, clothing);
    }
}

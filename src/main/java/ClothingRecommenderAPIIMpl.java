import domain.*;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import java.util.*;

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
    public Descriptor loadDescriptor(String value) {
        Session session = this.sessionFactory.getNeo4jSession();
        return session.load(Descriptor.class, value);
    }

    @Override
    public Descriptor loadDescriptor(String descriptorType, String value) {
        Session session = this.sessionFactory.getNeo4jSession();
        return session.load(Descriptor.class, value); //TODO: check sub classes
    }

    @Override
    public Grouping loadGrouping(String value) {
        Session session = this.sessionFactory.getNeo4jSession();
        return session.load(Grouping.class, value);
    }

    @Override
    public Grouping loadGrouping(String groupingType, String value) {
        Session session = this.sessionFactory.getNeo4jSession();
        return session.load(Grouping.class, value); //TODO: check collection/set
    }

    @Override
    public List<Clothing> recommendPurchase(String userID) {
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
        query.append("MATCH (selected:Clothing { catalogID: $cid }) \n");
        params.put("cid", selected.getCatalogId());

        query.append("MATCH (user:User { username: $userid }) \n");
        params.put("userid", userID);

        query.append("MATCH (selected)<-[:CLOTHING_DESCRIPTOR]-(descriptors:Descriptor)");
        query.append("-[descriptions:CLOTHING_DESCRIPTOR]->(in_descriptions:Clothing) \n");

        //2. Find all the other clothing that also have these descriptors
        query.append("WHERE NOT (in_descriptions)<-[:Owns]-(user) \n");

        // 2.2 that are not the same clothing type - Filter of same type
        query.append("AND NOT in_descriptions:").append(selected.getType().getType()).append(" \n");
        query.append("WITH in_descriptions, count(DISTINCT descriptions) as cnt \n");

        query.append("RETURN in_descriptions ORDER by cnt DESC\n");
//        query.append("ORDER BY count(descriptions);");

        Iterable<Clothing> result = session.query(Clothing.class, query.toString(), params);
        List<Clothing> clothings = new ArrayList<>();
        result.forEach(clothings::add);
        return clothings;
    }

    @Override
    public List<Clothing> recommendRelatedItems(String userID, Clothing selected) {
        Session session = this.sessionFactory.getNeo4jSession();

        Map<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder();

        // 1. find the groupings of the selected clothings
        // 2. Find all of the other clothings in those groupings
        query.append("MATCH (selected:Clothing { catalogID: $cid }) \n");
        params.put("cid", selected.getCatalogId());

        query.append("MATCH (user:User { username: $userid }) \n");
        params.put("userid", userID);

        query.append("MATCH (selected)<-[:CLOTHING_GROUPING]-(groupings:Grouping)");
        query.append("-[cg:CLOTHING_GROUPING]->(in_groupings:Clothing) \n");
        // 3. Filter by not already in the user's closet
        query.append("WHERE NOT (in_groupings)<-[:Owns]-(user) \n");

        // 2.2 that are not the same clothing type - Filter of same type
        query.append("AND NOT in_groupings:").append(selected.getType().getType()).append(" \n");
        query.append("WITH in_groupings, count(DISTINCT cg) as cnt \n");

        query.append("RETURN in_groupings ORDER by cnt DESC;");

        Result res = session.query(query.toString(), params);

        Iterable<Clothing> result = session.query(Clothing.class, query.toString(), params);
        List<Clothing> clothings = new ArrayList<>();
        result.forEach(clothings::add);
        return clothings;
    }

    private <T> Collection<T> listByLabel(Class<T> t) {
        Session session = this.sessionFactory.getNeo4jSession();
        return session.loadAll(t, 3);
    }

    @Override
    public Collection<User> listUsers() {
        return this.listByLabel(User.class);
    }

    @Override
    public Collection<Clothing> listClothings() {
        return this.listByLabel(Clothing.class);
    }

    @Override
    public Collection<BottomClothing> listBottoms() {
        return this.listByLabel(BottomClothing.class);
    }

    @Override
    public Collection<FootwearClothing> listFootwares() {
        return this.listByLabel(FootwearClothing.class);
    }

    @Override
    public Collection<TopClothing> listTops() {
        return this.listByLabel(TopClothing.class);
    }

    @Override
    public Collection<Descriptor> listDescriptors() {
        return this.listByLabel(Descriptor.class);
    }

    @Override
    public Collection<ColorDescriptor> listColorDescriptors() {
        return this.listByLabel(ColorDescriptor.class);
    }

    @Override
    public Collection<FancinessDescriptor> listFancinessDescriptors() {
        return this.listByLabel(FancinessDescriptor.class);
    }

    @Override
    public Collection<BrandDescriptor> listBrandDescriptors() {
        return this.listByLabel(BrandDescriptor.class);
    }

    @Override
    public Collection<SubtypeDescriptor> listSubtypeDescriptors() {
        return this.listByLabel(SubtypeDescriptor.class);
    }

    @Override
    public Collection<Grouping> listGroupings() {
        return this.listByLabel(Grouping.class);
    }

    @Override
    public Collection<CollectionGrouping> listCollectionGroupings() {
        return this.listByLabel(CollectionGrouping.class);
    }

    @Override
    public Collection<SetGrouping> listSetGroupings() {
        return this.listByLabel(SetGrouping.class);
    }

    @Override
    public void addToGrouping(String catalogID, Grouping grouping) {
        Session session = this.sessionFactory.getNeo4jSession();
        Clothing c = session.load(Clothing.class, catalogID);
        c.addGrouping(grouping);
        session.save(c);
    }

    @Override
    public void addToUserCloset(User user, Clothing clothing) {
        Session session = this.sessionFactory.getNeo4jSession();
        user.addToCloset(clothing);
        session.save(user);
    }

    @Override
    public void addToUserCloset(String username, Clothing clothing) {
        Session session = this.sessionFactory.getNeo4jSession();
        User user = session.load(User.class, username);
        this.addToUserCloset(user, clothing);
    }
}

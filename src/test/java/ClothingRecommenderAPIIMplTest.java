import domain.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@Testcontainers
public class ClothingRecommenderAPIIMplTest {

    @Container
    private static final Neo4jContainer databaseServer = new Neo4jContainer();

    static Neo4jSessionFactory sessionFactory;
    static ClothingRecommenderAPIIMpl api;
    static DescriptorFactory descriptorFactory;
    static private Session session;

    @BeforeAll
    static public void setUp() throws Exception {
        Configuration configuration = new Configuration.Builder()
                .uri(databaseServer.getBoltUrl())
                .credentials("neo4j", databaseServer.getAdminPassword())
                .build();

        sessionFactory = new Neo4jSessionFactory(new SessionFactory(configuration, "domain"));

        api = new ClothingRecommenderAPIIMpl(sessionFactory);

        session = sessionFactory.getNeo4jSession();
        session.purgeDatabase();

        descriptorFactory = new DescriptorFactory(sessionFactory);
    }

    @AfterAll
    static public void tearDown() throws Exception {
        if (session != null) {
            session.purgeDatabase();
            session.clear();
        }
    }

//    @Test
//    public void testEnterOutfit() {
//        //TODO: test
//    }

    @Test
    public void testEnterClothingItem() {

        List<Descriptor> descriptors = new ArrayList<Descriptor>();
        descriptors.add(descriptorFactory.make(AttributeTypes.COLOR, "green"));
        descriptors.add(descriptorFactory.make(AttributeTypes.COLOR, "black"));
        descriptors.add(descriptorFactory.make(AttributeTypes.COLOR, "blue"));
        descriptors.add(descriptorFactory.make(AttributeTypes.COLOR, "yellow"));
        descriptors.add(descriptorFactory.make(AttributeTypes.BRAND, "Gap"));
        descriptors.add(descriptorFactory.make(AttributeTypes.FANCINESS, "extra formal"));
        descriptors.add(descriptorFactory.make(AttributeTypes.SUBTYPE, "jeans"));

        api.enterClothingItem(123, ClothingType.BOTTOM, "Test Bottom 1", descriptors);

        Collection<Clothing> allClothings = session.loadAll(Clothing.class);
        assertEquals(allClothings.size(), 1);
        assertEquals(allClothings.iterator().next().getName(), "Test Bottom 1");

        Collection<Descriptor> allDescriptors = session.loadAll(Descriptor.class);
        assertEquals(allDescriptors.size(), 7);

        Collection<ColorDescriptor> allColors = session.loadAll(ColorDescriptor.class);
        assertEquals(allColors.size(), 4);

        Clothing clothing = session.load(Clothing.class, "Test Bottom 1");
        assertNotNull(clothing);
        assertEquals(clothing.getName(), "Test Bottom 1");
        assertEquals(clothing.getDescriptors().size(), 7);
        assertTrue(clothing.getDescriptors().containsAll(descriptors));
    }

//    @Test
//    public void testEnterUserPreference() {
//    }
//
//    @Test
//    public void testRecommendOutfit1() {
//    }
//
//    @Test
//    public void testRecommendOutfit2() {
//    }
}
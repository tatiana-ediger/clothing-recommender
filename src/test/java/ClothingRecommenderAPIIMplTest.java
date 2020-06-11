import domain.*;
import org.junit.jupiter.api.*;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

    @BeforeEach
    public void startupTestData() {
        StringBuilder query = new StringBuilder();
        query.append("CREATE (bluejeans:Clothing:Bottom { name: 'bluejeans' })\n");
        query.append("CREATE (blackslacks:Clothing:Bottom { name: 'blackslacks' })\n");
        query.append("CREATE (blackjeans:Clothing:Bottom { name: 'blackjeans' })\n");
        query.append("CREATE (khakislacks:Clothing:Bottom { name: 'khakislacks' })\n");
        query.append("CREATE (grayjeans:Clothing:Bottom { name: 'grayjeans' })\n");
        query.append("CREATE (grayshorts:Clothing:Bottom { name: 'grayshorts' })\n");
        query.append("CREATE (redskirt:Clothing:Bottom { name: 'redskirt' })\n");
        query.append("CREATE (blackskirt:Clothing:Bottom { name: 'blackskirt' })\n");

        //query.append("CREATE (reddress:Clothing:Bottom:Top { name: 'reddress' })\n");

        query.append("CREATE (whitebuttonup:Clothing:Top { name: 'whitebuttonup' })\n");
        query.append("CREATE (blacktshirt:Clothing:Top { name: 'blacktshirt' })\n");
        query.append("CREATE (redtshirt:Clothing:Top { name: 'redtshirt' })\n");
        query.append("CREATE (greentshirt:Clothing:Top { name: 'greentshirt' })\n");
        query.append("CREATE (bluebuttonup:Clothing:Top { name: 'bluebuttonup' })\n");
        query.append("CREATE (whiteblouse:Clothing:Top { name: 'whiteblouse' })\n");
        query.append("CREATE (blackfancytop:Clothing:Top { name: 'blackfancytop' })\n");
        query.append("CREATE (graysweater:Clothing:Top { name: 'graysweater' })\n");

        query.append("CREATE (whitetvans:Clothing:Footwear { name: 'whitetvans' })\n");
        query.append("CREATE (blacksneakers:Clothing:Footwear { name: 'blacksneakers' })\n");
        query.append("CREATE (blackdressshoes:Clothing:Footwear { name: 'blackdressshoes' })\n");
        query.append("CREATE (browndressshoes:Clothing:Footwear { name: 'browndressshoes' })\n");
        query.append("CREATE (blackvans:Clothing:Footwear { name: 'blackvans' })\n");
        query.append("CREATE (blackheels:Clothing:Footwear { name: 'blackheels' })\n");
        query.append("CREATE (brownsandals:Clothing:Footwear { name: 'brownsandals' })\n");

        query.append("CREATE (jeans:Descriptor:Subtype {name: 'jeans'})\n");
        query.append("CREATE (slacks:Descriptor:Subtype {name: 'slacks'})\n");
        query.append("CREATE (skirt:Descriptor:Subtype {name: 'skirt'})\n");
        query.append("CREATE (sandals:Descriptor:Subtype {name: 'andals'})\n");
        query.append("CREATE (dressshoes:Descriptor:Subtype {name: 'ressshoes'})\n");
        query.append("CREATE (heels:Descriptor:Subtype {name: 'eels'})\n");
        query.append("CREATE (red:Descriptor:Color {name:'red'})\n");
        query.append("CREATE (blue:Descriptor:Color {name:'blue'})\n");
        query.append("CREATE (black:Descriptor:Color {name:'black'})\n");
        query.append("CREATE (white:Descriptor:Color {name:'white'})\n");
        query.append("CREATE (green:Descriptor:Color {name:'green'})\n");
        query.append("CREATE (gray:Descriptor:Color {name:'gray'})\n");
        query.append("CREATE (brown:Descriptor:Color {name:'brown'})\n");
        query.append("CREATE (vans:Descriptor:Brand {name: 'Vans'})\n");
        query.append("CREATE (nike:Descriptor:Brand {name: 'Nike'})\n");
        query.append("CREATE (fancy:Descriptor:Fanciness {name: 'fancy'})\n");
        query.append("CREATE (casual:Descriptor:Fanciness {name: 'casual'})\n");

        query.append("CREATE (jeans)-[:CLOTHING_DESCRIPTOR]->(bluejeans)");
        query.append("CREATE (jeans)-[:CLOTHING_DESCRIPTOR]->(grayjeans)\n");
        query.append("CREATE (jeans)-[:CLOTHING_DESCRIPTOR]->(blackjeans)\n");
        query.append("CREATE (red)-[:CLOTHING_DESCRIPTOR]->(redtshirt)\n");
        query.append("CREATE (red)-[:CLOTHING_DESCRIPTOR]->(redskirt)\n");
        query.append("CREATE (red)-[:CLOTHING_DESCRIPTOR]->(reddress)\n");
        query.append("CREATE (fancy)-[:CLOTHING_DESCRIPTOR]->(redskirt)\n");
        query.append("CREATE (fancy)-[:CLOTHING_DESCRIPTOR]->(blackfancytop)\n");
        query.append("CREATE (fancy)-[:CLOTHING_DESCRIPTOR]->(whiteblouse)\n");
        query.append("CREATE (fancy)-[:CLOTHING_DESCRIPTOR]->(whitebuttonup)");
        query.append("CREATE (casual)-[:CLOTHING_DESCRIPTOR]->(redtshirt)\n");
        query.append("CREATE (casual)-[:CLOTHING_DESCRIPTOR]->(whitetshirt)\n");
        query.append("CREATE (casual)-[:CLOTHING_DESCRIPTOR]->(greentshirt)\n");
        query.append("CREATE (casual)-[:CLOTHING_DESCRIPTOR]->(blueshorts)\n");
        query.append("CREATE (casual)-[:CLOTHING_DESCRIPTOR]->(grayshorts)\n");

        session.query(query.toString(), new HashMap<>());
    }

    @AfterEach
    public void cleanTestData() {
        session.purgeDatabase();
        session.clear();
    }

//    @Test
//    public void testEnterOutfit() {
//        //TODO: test
//    }

    @Test
    public void testEnterClothingItem() {

        List<Descriptor> descriptors = new ArrayList<Descriptor>();
        descriptors.add(descriptorFactory.make(DescriptorTypes.COLOR, "green"));
        descriptors.add(descriptorFactory.make(DescriptorTypes.COLOR, "black"));
        descriptors.add(descriptorFactory.make(DescriptorTypes.COLOR, "blue"));
        descriptors.add(descriptorFactory.make(DescriptorTypes.COLOR, "yellow"));
        descriptors.add(descriptorFactory.make(DescriptorTypes.BRAND, "Gap"));
        descriptors.add(descriptorFactory.make(DescriptorTypes.FANCINESS, "extra formal"));
        descriptors.add(descriptorFactory.make(DescriptorTypes.SUBTYPE, "jeans"));

        api.addToCatalog(ClothingType.BOTTOM, "Test Bottom 1", descriptors);

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

    @Test
    void recommendPurchaseTogether() {
        Clothing c = session.load(Clothing.class, new Long(1));
        api.recommendPurchaseTogether(1, c);
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
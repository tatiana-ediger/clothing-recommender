import domain.*;
import org.junit.Rule;
import org.neo4j.harness.junit.Neo4jRule;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

class ClothingRecommenderAPIIMplTest {

    @Rule
    public Neo4jRule neoServer = new Neo4jRule();

    Neo4jSessionFactory sessionFactory;
    ClothingRecommenderAPIIMpl api;
    DescriptorFactory descriptorFactory;
    private Session session;

    @org.junit.Before
    public void setUp() throws Exception {
        Configuration configuration = new Configuration.Builder()
                .uri(this.neoServer.boltURI().toString())
                .build();

        SessionFactory sessionFactory = new SessionFactory(configuration, "domain");

        this.sessionFactory = new Neo4jSessionFactory(sessionFactory);

        this.api = new ClothingRecommenderAPIIMpl(this.sessionFactory);

        this.session = sessionFactory.openSession();
        this.session.purgeDatabase();

        this.descriptorFactory = new DescriptorFactory(this.sessionFactory);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        this.session.purgeDatabase();
        this.session.clear();
    }

    @org.junit.Test
    public void testEnterOutfit() {
        //TODO: test
    }

    @org.junit.Test
    public void testEnterClothingItem() {
        List<Descriptor> descriptors = new ArrayList<Descriptor>();
        descriptors.add(this.descriptorFactory.make(AttributeTypes.COLOR, "green"));
        descriptors.add(this.descriptorFactory.make(AttributeTypes.COLOR, "black"));
        descriptors.add(this.descriptorFactory.make(AttributeTypes.COLOR, "blue"));
        descriptors.add(this.descriptorFactory.make(AttributeTypes.COLOR, "yellow"));
        descriptors.add(this.descriptorFactory.make(AttributeTypes.BRAND, "Gap"));
        descriptors.add(this.descriptorFactory.make(AttributeTypes.FANCINESS, "extra formal"));
        descriptors.add(this.descriptorFactory.make(AttributeTypes.SUBTYPE, "jeans"));

        this.api.enterClothingItem(123, ClothingType.BOTTOM, "Test Bottom 1", descriptors);

        Collection<Clothing> allClothings = this.session.loadAll(Clothing.class);
        assertEquals(allClothings.size(), 1);
        assertEquals(allClothings.iterator().next().getName(), "Test Bottom 1");

        Collection<Descriptor> allDescriptors = this.session.loadAll(Descriptor.class);
        assertEquals(allDescriptors.size(), 7);

        Collection<ColorDescriptor> allColors = this.session.loadAll(ColorDescriptor.class);
        assertEquals(allColors.size(), 4);

        Clothing clothing = this.session.load(Clothing.class, "Test Bottom 1");
        assertNotNull(clothing);
        assertEquals(clothing.getName(), "Test Bottom 1");
        assertEquals(clothing.getDescriptors().size(), 7);
        assertTrue(clothing.getDescriptors().containsAll(descriptors));
    }

    @org.junit.Test
    public void testEnterUserPreference() {
    }

    @org.junit.Test
    public void testRecommendOutfit1() {
    }

    @org.junit.Test
    public void testRecommendOutfit2() {
    }
}
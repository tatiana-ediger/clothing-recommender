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
        Descriptor jeans = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "jeans", this.session);
        Descriptor slacks = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "slacks", this.session);
        Descriptor shorts = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "shorts", this.session);
        Descriptor skirt = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "skirt", this.session);
        Descriptor sandals = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "andals", this.session);
        Descriptor dress_shoes = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "dress_shoes", this.session);
        Descriptor heels = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "heels", this.session);
        Descriptor button_up = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "button_up", this.session);
        Descriptor blouse = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "blouse", this.session);
        Descriptor top = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "top", this.session);
        Descriptor sweater = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "sweater", this.session);
        Descriptor t_shirt = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "t_shirt", this.session);
        Descriptor red = DescriptorFactory.make(DescriptorTypes.COLOR, "red", this.session);
        Descriptor blue = DescriptorFactory.make(DescriptorTypes.COLOR, "blue", this.session);
        Descriptor black = DescriptorFactory.make(DescriptorTypes.COLOR, "black", this.session);
        Descriptor white = DescriptorFactory.make(DescriptorTypes.COLOR, "white", this.session);
        Descriptor khaki = DescriptorFactory.make(DescriptorTypes.COLOR, "khaki", this.session);
        Descriptor green = DescriptorFactory.make(DescriptorTypes.COLOR, "green", this.session);
        Descriptor gray = DescriptorFactory.make(DescriptorTypes.COLOR, "gray", this.session);
        Descriptor brown = DescriptorFactory.make(DescriptorTypes.COLOR, "brown", this.session);
        Descriptor vans = DescriptorFactory.make(DescriptorTypes.BRAND, "Vans", this.session);
        Descriptor nike = DescriptorFactory.make(DescriptorTypes.BRAND, "Nike", this.session);
        Descriptor birkenstock = DescriptorFactory.make(DescriptorTypes.BRAND, "Birkenstock", this.session);
        Descriptor fancy = DescriptorFactory.make(DescriptorTypes.FANCINESS, "fancy", this.session);
        Descriptor casual = DescriptorFactory.make(DescriptorTypes.FANCINESS, "casual", this.session);

        Clothing blue_jeans = ClothingFactory.make(ClothingType.BOTTOM, "Blue Jeans");
        blue_jeans.addDescriptor(jeans);
        blue_jeans.addDescriptor(blue);
        session.save(blue_jeans);

        Clothing blue_shorts = ClothingFactory.make(ClothingType.BOTTOM, "Blue Shorts");
        blue_shorts.addDescriptor(casual);
        blue_shorts.addDescriptor(blue);
        blue_shorts.addDescriptor(shorts);
        session.save(blue_shorts);

        Clothing black_slacks = ClothingFactory.make(ClothingType.BOTTOM, "Black Slacks");
        black_slacks.addDescriptor(black);
        black_slacks.addDescriptor(slacks);
        session.save(black_slacks);

        Clothing black_jeans = ClothingFactory.make(ClothingType.BOTTOM, "Black Jeans");
        black_jeans.addDescriptor(jeans);
        black_jeans.addDescriptor(black);
        session.save(black_jeans);

        Clothing khaki_slacks = ClothingFactory.make(ClothingType.BOTTOM, "Khaki Slacks");
        khaki_slacks.addDescriptor(slacks);
        khaki_slacks.addDescriptor(khaki);
        session.save(khaki_slacks);

        Clothing gray_jeans = ClothingFactory.make(ClothingType.BOTTOM, "Gray Jeans");
        gray_jeans.addDescriptor(jeans);
        gray_jeans.addDescriptor(gray);
        session.save(gray_jeans);

        Clothing gray_shorts = ClothingFactory.make(ClothingType.BOTTOM, "Gray Shorts");
        gray_shorts.addDescriptor(casual);
        gray_shorts.addDescriptor(gray);
        gray_shorts.addDescriptor(shorts);
        session.save(gray_shorts);

        Clothing red_skirt = ClothingFactory.make(ClothingType.BOTTOM, "Red Skirt");
        red_skirt.addDescriptor(fancy);
        red_skirt.addDescriptor(red);
        red_skirt.addDescriptor(skirt);
        session.save(red_skirt);

        Clothing black_skirt = ClothingFactory.make(ClothingType.BOTTOM, "Black Skirt");
        black_skirt.addDescriptor(black);
        black_skirt.addDescriptor(skirt);
        black_skirt.addDescriptor(casual);
        session.save(black_skirt);

        Clothing fancy_black_skirt = ClothingFactory.make(ClothingType.BOTTOM, "Fancy Black Skirt");
        fancy_black_skirt.addDescriptor(black);
        fancy_black_skirt.addDescriptor(skirt);
        fancy_black_skirt.addDescriptor(fancy);
        session.save(fancy_black_skirt);

        //TODO: figure out how to represent clothing that is both top and bottom
        //query.append("CREATE (reddress:Clothing:Bottom:Top { name: 'reddress' })\n");
        //        reddress.addDescriptor(red);


        Clothing white_buttonup = ClothingFactory.make(ClothingType.TOP, "White Buttonup");
        white_buttonup.addDescriptor(fancy);
        white_buttonup.addDescriptor(white);
        white_buttonup.addDescriptor(button_up);
        session.save(white_buttonup);

        Clothing black_tshirt = ClothingFactory.make(ClothingType.TOP, "Black Tshirt");
        black_tshirt.addDescriptor(black);
        black_tshirt.addDescriptor(t_shirt);
        black_tshirt.addDescriptor(casual);
        session.save(black_tshirt);

        Clothing red_tshirt = ClothingFactory.make(ClothingType.TOP, "Red Tshirt");
        red_tshirt.addDescriptor(red);
        red_tshirt.addDescriptor(casual);
        red_tshirt.addDescriptor(t_shirt);
        session.save(red_tshirt);

        Clothing white_tshirt = ClothingFactory.make(ClothingType.TOP, "White Tshirt");
        white_tshirt.addDescriptor(casual);
        white_tshirt.addDescriptor(t_shirt);
        white_tshirt.addDescriptor(white);
        session.save(white_tshirt);

        Clothing fancy_white_tshirt = ClothingFactory.make(ClothingType.TOP, "Fancy White Tshirt");
        fancy_white_tshirt.addDescriptor(fancy);
        fancy_white_tshirt.addDescriptor(t_shirt);
        fancy_white_tshirt.addDescriptor(white);
        session.save(fancy_white_tshirt);

        Clothing green_tshirt = ClothingFactory.make(ClothingType.TOP, "Green Tshirt");
        green_tshirt.addDescriptor(casual);
        green_tshirt.addDescriptor(t_shirt);
        green_tshirt.addDescriptor(green);
        session.save(green_tshirt);

        Clothing blue_buttonup = ClothingFactory.make(ClothingType.TOP, "Blue Buttonup");
        blue_buttonup.addDescriptor(blue);
        blue_buttonup.addDescriptor(button_up);
        session.save(blue_buttonup);

        Clothing white_blouse = ClothingFactory.make(ClothingType.TOP, "White Blouse");
        white_blouse.addDescriptor(fancy);
        white_blouse.addDescriptor(white);
        white_blouse.addDescriptor(blouse);
        session.save(white_blouse);

        Clothing black_fancy_top = ClothingFactory.make(ClothingType.TOP, "Black Fancy Top");
        black_fancy_top.addDescriptor(fancy);
        black_fancy_top.addDescriptor(black);
        black_fancy_top.addDescriptor(top);
        session.save(black_fancy_top);

        Clothing gray_sweater = ClothingFactory.make(ClothingType.TOP, "Gray Sweater");
        gray_sweater.addDescriptor(gray);
        gray_sweater.addDescriptor(sweater);
        gray_sweater.addDescriptor(fancy);
        session.save(gray_sweater);

        Clothing white_vans = ClothingFactory.make(ClothingType.FOOTWEAR, "White Vans");
        white_vans.addDescriptor(white);
        white_vans.addDescriptor(vans);
        session.save(white_vans);

        Clothing black_sneakers = ClothingFactory.make(ClothingType.FOOTWEAR, "Black Sneakers");
        black_sneakers.addDescriptor(black);
        black_sneakers.addDescriptor(casual);
        black_sneakers.addDescriptor(nike);
        session.save(black_sneakers);

        Clothing black_dress_shoes = ClothingFactory.make(ClothingType.FOOTWEAR, "Black Dress Shoes");
        black_dress_shoes.addDescriptor(black);
        black_dress_shoes.addDescriptor(dress_shoes);
        black_dress_shoes.addDescriptor(fancy);
        session.save(black_dress_shoes);

        Clothing brown_dress_shoes = ClothingFactory.make(ClothingType.FOOTWEAR, "Brown Dress Shoes");
        brown_dress_shoes.addDescriptor(brown);
        brown_dress_shoes.addDescriptor(dress_shoes);
        brown_dress_shoes.addDescriptor(fancy);
        session.save(brown_dress_shoes);

        Clothing black_vans = ClothingFactory.make(ClothingType.FOOTWEAR, "Black Vans");
        black_vans.addDescriptor(black);
        black_vans.addDescriptor(vans);
        session.save(black_vans);

        Clothing black_heels = ClothingFactory.make(ClothingType.FOOTWEAR, "Black Heels");
        black_heels.addDescriptor(black);
        black_heels.addDescriptor(fancy);
        black_heels.addDescriptor(heels);
        session.save(black_heels);

        Clothing brown_sandals = ClothingFactory.make(ClothingType.FOOTWEAR, "Brown Sandals");
        brown_sandals.addDescriptor(brown);
        brown_sandals.addDescriptor(sandals);
        brown_sandals.addDescriptor(casual);
        brown_sandals.addDescriptor(birkenstock);
        session.save(brown_sandals);
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
        descriptors.add(descriptorFactory.make(DescriptorTypes.COLOR, "green", this.session));
        descriptors.add(descriptorFactory.make(DescriptorTypes.COLOR, "black", this.session));
        descriptors.add(descriptorFactory.make(DescriptorTypes.COLOR, "blue", this.session));
        descriptors.add(descriptorFactory.make(DescriptorTypes.COLOR, "yellow", this.session));
        descriptors.add(descriptorFactory.make(DescriptorTypes.BRAND, "Gap", this.session));
        descriptors.add(descriptorFactory.make(DescriptorTypes.FANCINESS, "extra formal", this.session));
        descriptors.add(descriptorFactory.make(DescriptorTypes.SUBTYPE, "jeans", this.session));

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
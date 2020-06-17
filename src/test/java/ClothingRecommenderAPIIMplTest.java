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
    private static final Neo4jContainer databaseServer = new Neo4jContainer().withoutAuthentication();

    static Neo4jSessionFactory sessionFactory;
    static ClothingRecommenderAPIIMpl api;
    static private Session session;

    @BeforeAll
    static public void setUp() throws Exception {

        databaseServer.withExposedPorts(7474, 7687);

        Configuration configuration = new Configuration.Builder()
                .uri(databaseServer.getBoltUrl())
                //.credentials("neo4j", databaseServer.getAdminPassword())
                .build();


        sessionFactory = new Neo4jSessionFactory(new SessionFactory(configuration, "domain"));

        api = new ClothingRecommenderAPIIMpl(sessionFactory);

        session = sessionFactory.getNeo4jSession();
        session.purgeDatabase();

        int mapped7474 = databaseServer.getMappedPort(7474);
        System.out.println("Mapped port for Neo4j 7474: " + mapped7474);
        int mapped7687 = databaseServer.getMappedPort(7687);
        System.out.println("Mapped port for Neo4j 7687: " + mapped7687);
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
        Descriptor sandals = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "sandals", this.session);
        Descriptor suit_blazer = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "suit_blazer", this.session);
        Descriptor suit_trousers = DescriptorFactory.make(DescriptorTypes.SUBTYPE, "suit_trousers", this.session);
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

        Grouping fall_collection = GroupingFactory.make(GroupingTypes.COLLECTION, "Fall 2020", this.session);
        Grouping winter_collection = GroupingFactory.make(GroupingTypes.COLLECTION, "Winter 2050", this.session);
        Grouping blm_collection = GroupingFactory.make(GroupingTypes.COLLECTION, "BLM Collection", this.session);
        Grouping summer_collection = GroupingFactory.make(GroupingTypes.COLLECTION, "Summer 1999", this.session);
        Grouping fancy_wedding_suit_set = GroupingFactory.make(GroupingTypes.SET, "Wedding Suit", this.session);
        Grouping fancy_party_set = GroupingFactory.make(GroupingTypes.SET, "Fancy Party Set", this.session);
        Grouping casual_blue_set = GroupingFactory.make(GroupingTypes.SET, "Day in the Park (blue)", this.session);
        Grouping casual_black_set = GroupingFactory.make(GroupingTypes.SET, "Day in the Park (black)", this.session);

        Clothing blue_jeans = ClothingFactory.make(ClothingType.BOTTOM, "CID_blueJeans",
                "Blue Jeans");
        blue_jeans.addDescriptor(jeans);
        blue_jeans.addDescriptor(blue);
        blue_jeans.addGrouping(fall_collection);
        blue_jeans.addGrouping(casual_blue_set);
        session.save(blue_jeans);

        Clothing blue_shorts = ClothingFactory.make(ClothingType.BOTTOM, "CID_blueShorts",
                "Blue Shorts");
        blue_shorts.addDescriptor(casual);
        blue_shorts.addDescriptor(blue);
        blue_shorts.addDescriptor(shorts);
        blue_shorts.addGrouping(summer_collection);
        blue_shorts.addGrouping(casual_blue_set);
        session.save(blue_shorts);

        Clothing black_slacks = ClothingFactory.make(ClothingType.BOTTOM, "CID_blackSlacks",
                "Black Slacks");
        black_slacks.addDescriptor(black);
        black_slacks.addDescriptor(slacks);
        black_slacks.addGrouping(fall_collection);
        session.save(black_slacks);

        Clothing black_jeans = ClothingFactory.make(ClothingType.BOTTOM, "CID_blackJeans",
                "Black Jeans");
        black_jeans.addDescriptor(jeans);
        black_jeans.addDescriptor(black);
        black_jeans.addGrouping(summer_collection);
        black_jeans.addGrouping(casual_black_set);
        black_jeans.addGrouping(blm_collection);
        session.save(black_jeans);

        Clothing khaki_slacks = ClothingFactory.make(ClothingType.BOTTOM, "CID_khakiSlacks",
                "Khaki Slacks");
        khaki_slacks.addDescriptor(slacks);
        khaki_slacks.addDescriptor(khaki);
        khaki_slacks.addGrouping(fall_collection);
        session.save(khaki_slacks);

        Clothing gray_jeans = ClothingFactory.make(ClothingType.BOTTOM, "CID_grayJeans",
                "Gray Jeans");
        gray_jeans.addDescriptor(jeans);
        gray_jeans.addDescriptor(gray);
        gray_jeans.addGrouping(fall_collection);
        session.save(gray_jeans);

        Clothing gray_shorts = ClothingFactory.make(ClothingType.BOTTOM, "CID_grayShorts",
                "Gray Shorts");
        gray_shorts.addDescriptor(casual);
        gray_shorts.addDescriptor(gray);
        gray_shorts.addDescriptor(shorts);
        gray_shorts.addGrouping(summer_collection);
        session.save(gray_shorts);

        Clothing red_skirt = ClothingFactory.make(ClothingType.BOTTOM, "CID_redSkirt",
                "Red Skirt");
        red_skirt.addDescriptor(fancy);
        red_skirt.addDescriptor(red);
        red_skirt.addDescriptor(skirt);
        red_skirt.addGrouping(summer_collection);
        session.save(red_skirt);

        Clothing black_skirt = ClothingFactory.make(ClothingType.BOTTOM, "CID_blackSkirt",
                "Black Skirt");
        black_skirt.addDescriptor(black);
        black_skirt.addDescriptor(skirt);
        black_skirt.addDescriptor(casual);
        black_skirt.addGrouping(summer_collection);
        black_skirt.addGrouping(casual_black_set);
        black_skirt.addGrouping(blm_collection);
        session.save(black_skirt);

        Clothing fancy_black_skirt = ClothingFactory.make(ClothingType.BOTTOM, "CID_fancyBlackSkirt",
                "Fancy Black Skirt");
        fancy_black_skirt.addDescriptor(black);
        fancy_black_skirt.addDescriptor(skirt);
        fancy_black_skirt.addDescriptor(fancy);
        fancy_black_skirt.addGrouping(fancy_party_set);
        session.save(fancy_black_skirt);

        Clothing white_buttonup = ClothingFactory.make(ClothingType.TOP, "CID_whiteButtonup",
                "White Buttonup");
        white_buttonup.addDescriptor(fancy);
        white_buttonup.addDescriptor(white);
        white_buttonup.addDescriptor(button_up);
        white_buttonup.addGrouping(fancy_wedding_suit_set);
        session.save(white_buttonup);

        Clothing black_tshirt = ClothingFactory.make(ClothingType.TOP, "CID_blackT",
                "Black Tshirt");
        black_tshirt.addDescriptor(black);
        black_tshirt.addDescriptor(t_shirt);
        black_tshirt.addDescriptor(casual);
        black_tshirt.addGrouping(summer_collection);
        black_tshirt.addGrouping(blm_collection);
        session.save(black_tshirt);

        Clothing red_tshirt = ClothingFactory.make(ClothingType.TOP, "CID_redT",
                "Red Tshirt");
        red_tshirt.addDescriptor(red);
        red_tshirt.addDescriptor(casual);
        red_tshirt.addDescriptor(t_shirt);
        red_tshirt.addGrouping(summer_collection);
        session.save(red_tshirt);

        Clothing white_tshirt = ClothingFactory.make(ClothingType.TOP, "CID_whiteT",
                "White Tshirt");
        white_tshirt.addDescriptor(casual);
        white_tshirt.addDescriptor(t_shirt);
        white_tshirt.addDescriptor(white);
        white_tshirt.addGrouping(summer_collection);
        session.save(white_tshirt);

        Clothing fancy_white_blouse = ClothingFactory.make(ClothingType.TOP, "CID_fancyWhiteBlouse",
                "Fancy White Blouse");
        fancy_white_blouse.addDescriptor(fancy);
        fancy_white_blouse.addDescriptor(blouse);
        fancy_white_blouse.addDescriptor(white);
        fancy_white_blouse.addGrouping(fancy_party_set);
        fancy_white_blouse.addGrouping(fall_collection);
        session.save(fancy_white_blouse);

        Clothing green_tshirt = ClothingFactory.make(ClothingType.TOP, "CID_greenT",
                "Green Tshirt");
        green_tshirt.addDescriptor(casual);
        green_tshirt.addDescriptor(t_shirt);
        green_tshirt.addDescriptor(green);
        green_tshirt.addGrouping(summer_collection);
        session.save(green_tshirt);

        Clothing blue_buttonup = ClothingFactory.make(ClothingType.TOP, "CID_blueButtonup",
                "Blue Buttonup");
        blue_buttonup.addDescriptor(blue);
        blue_buttonup.addDescriptor(button_up);
        blue_buttonup.addGrouping(summer_collection);
        session.save(blue_buttonup);

        Clothing white_blouse = ClothingFactory.make(ClothingType.TOP, "CID_whiteBlouse",
                "White Blouse");
        white_blouse.addDescriptor(fancy);
        white_blouse.addDescriptor(white);
        white_blouse.addDescriptor(blouse);
        white_blouse.addGrouping(fall_collection);
        session.save(white_blouse);

        Clothing black_fancy_top = ClothingFactory.make(ClothingType.TOP, "CID_blackFancy",
                "Black Fancy Top");
        black_fancy_top.addDescriptor(fancy);
        black_fancy_top.addDescriptor(black);
        black_fancy_top.addDescriptor(top);
        black_fancy_top.addGrouping(fall_collection);
        black_fancy_top.addGrouping(blm_collection);
        session.save(black_fancy_top);

        Clothing gray_sweater = ClothingFactory.make(ClothingType.TOP, "CID_graysweater",
                "Gray Sweater");
        gray_sweater.addDescriptor(gray);
        gray_sweater.addDescriptor(sweater);
        gray_sweater.addDescriptor(fancy);
        gray_sweater.addGrouping(winter_collection);
        session.save(gray_sweater);

        Clothing white_vans = ClothingFactory.make(ClothingType.FOOTWEAR, "CID_whiteVans",
                "White Vans");
        white_vans.addDescriptor(white);
        white_vans.addDescriptor(vans);
        white_vans.addGrouping(fall_collection);
        session.save(white_vans);

        Clothing black_sneakers = ClothingFactory.make(ClothingType.FOOTWEAR, "CID_blackSneakers",
                "Black Sneakers");
        black_sneakers.addDescriptor(black);
        black_sneakers.addDescriptor(casual);
        black_sneakers.addDescriptor(nike);
        black_sneakers.addGrouping(blm_collection);
        session.save(black_sneakers);

        Clothing black_dress_shoes = ClothingFactory.make(ClothingType.FOOTWEAR, "CID_blackDressShoes",
                "Black Dress Shoes");
        black_dress_shoes.addDescriptor(black);
        black_dress_shoes.addDescriptor(dress_shoes);
        black_dress_shoes.addDescriptor(fancy);
        black_dress_shoes.addGrouping(fancy_wedding_suit_set);
        session.save(black_dress_shoes);

        Clothing brown_dress_shoes = ClothingFactory.make(ClothingType.FOOTWEAR, "CID_brownDressShoes",
                "Brown Dress Shoes");
        brown_dress_shoes.addDescriptor(brown);
        brown_dress_shoes.addDescriptor(dress_shoes);
        brown_dress_shoes.addDescriptor(fancy);
        session.save(brown_dress_shoes);

        Clothing black_vans = ClothingFactory.make(ClothingType.FOOTWEAR, "CID_blackVans",
                "Black Vans");
        black_vans.addDescriptor(black);
        black_vans.addDescriptor(vans);
        black_vans.addGrouping(blm_collection);
        session.save(black_vans);

        Clothing black_heels = ClothingFactory.make(ClothingType.FOOTWEAR, "CID_blackHeels",
                "Black Heels");
        black_heels.addDescriptor(black);
        black_heels.addDescriptor(fancy);
        black_heels.addDescriptor(heels);
        black_heels.addGrouping(fancy_party_set);
        session.save(black_heels);

        Clothing brown_sandals = ClothingFactory.make(ClothingType.FOOTWEAR, "CID_brownSandals",
                "Brown Sandals");
        brown_sandals.addDescriptor(brown);
        brown_sandals.addDescriptor(sandals);
        brown_sandals.addDescriptor(casual);
        brown_sandals.addDescriptor(birkenstock);
        brown_sandals.addGrouping(summer_collection);
        session.save(brown_sandals);

        Clothing black_fancy_suit_trousers = ClothingFactory.make(ClothingType.BOTTOM, "CID_fancySuitTrousers",
                "Fancy Black Suit Trousers");
        black_fancy_suit_trousers.addDescriptor(black);
        black_fancy_suit_trousers.addDescriptor(fancy);
        black_fancy_suit_trousers.addDescriptor(suit_trousers);
        black_fancy_suit_trousers.addGrouping(winter_collection);
        black_fancy_suit_trousers.addGrouping(fancy_wedding_suit_set);
        session.save(black_fancy_suit_trousers);

        Clothing black_fancy_suit_blazer = ClothingFactory.make(ClothingType.TOP, "CID_fancySuitBlazer",
                "Fancy Black Suit Blazer");
        black_fancy_suit_blazer.addDescriptor(black);
        black_fancy_suit_blazer.addDescriptor(fancy);
        black_fancy_suit_blazer.addDescriptor(suit_blazer);
        black_fancy_suit_blazer.addGrouping(winter_collection);
        black_fancy_suit_blazer.addGrouping(fancy_wedding_suit_set);
        session.save(black_fancy_suit_blazer);

        Clothing red_heels = ClothingFactory.make(ClothingType.FOOTWEAR, "CID_redHeels",
                "Red Heels");
        red_heels.addDescriptor(red);
        red_heels.addDescriptor(fancy);
        red_heels.addDescriptor(casual);
        red_heels.addDescriptor(heels);
        red_heels.addGrouping(blm_collection);
        red_heels.addGrouping(summer_collection);
        session.save(red_heels);

        User user1 = new User("pablo42", "Pablo");
        user1.addToCloset(black_fancy_suit_blazer);
        user1.addToCloset(black_fancy_suit_trousers);
        user1.addToCloset(black_jeans);
        user1.addToCloset(green_tshirt);
        user1.addToCloset(blue_shorts);
        for (Clothing c : summer_collection.getClothings()) //Add every thing from summer
            user1.addToCloset(c);
        for (Clothing c : blue.getClothings()) //Add every thing that is blue
            user1.addToCloset(c);
        session.save(user1, 2);

        User user2 = new User("hermione", "Emma Watson");
        user2.addToCloset(black_fancy_suit_blazer);
        user2.addToCloset(red_skirt);
        user2.addToCloset(red_tshirt);
        user2.addToCloset(black_heels);
        user2.addToCloset(brown_sandals);
        user2.addToCloset(fancy_white_blouse);
        user2.addToCloset(black_fancy_top);
        for (Clothing c : black.getClothings()) //Add every thing that is black
            user2.addToCloset(c);
        session.save(user2, 2);

        User user3 = new User("ash", "Ashley");
        user3.addToCloset(black_fancy_suit_blazer);
        user3.addToCloset(red_skirt);
        user3.addToCloset(red_tshirt);
        user3.addToCloset(black_heels);
        user3.addToCloset(brown_sandals);
        user3.addToCloset(fancy_white_blouse);
        user3.addToCloset(fancy_black_skirt);
        user3.addToCloset(black_fancy_top);
        user3.addToCloset(gray_sweater);
        for (Clothing c : black.getClothings()) //Add every thing that is black
            user3.addToCloset(c);
        session.save(user3, 2);

        User user4 = new User("char", "Charlotte");
        user4.addToCloset(gray_jeans);
        user4.addToCloset(gray_sweater);
        user4.addToCloset(green_tshirt);
        user4.addToCloset(white_blouse);
        user4.addToCloset(white_buttonup);
        user4.addToCloset(white_vans);
        user4.addToCloset(white_tshirt);
        user4.addToCloset(black_skirt);
        user4.addToCloset(red_heels);
        session.save(user4, 2);
    }

    @AfterEach
    public void cleanTestData() {
        session.purgeDatabase();
        session.clear();
    }

    @Test
    public void testEnterClothingItem() {

        Collection<BottomClothing> allBottoms = session.loadAll(BottomClothing.class);
        int before = allBottoms.size();

        List<Descriptor> descriptors = new ArrayList<Descriptor>();
        descriptors.add(DescriptorFactory.make(DescriptorTypes.COLOR, "green", this.session));
        descriptors.add(DescriptorFactory.make(DescriptorTypes.COLOR, "black", this.session));
        descriptors.add(DescriptorFactory.make(DescriptorTypes.COLOR, "blue", this.session));
        descriptors.add(DescriptorFactory.make(DescriptorTypes.COLOR, "yellow", this.session));
        descriptors.add(DescriptorFactory.make(DescriptorTypes.BRAND, "gap", this.session));
        descriptors.add(DescriptorFactory.make(DescriptorTypes.FANCINESS, "extra formal", this.session));
        descriptors.add(DescriptorFactory.make(DescriptorTypes.SUBTYPE, "slacks", this.session));

        List<Grouping> groupings = new ArrayList<Grouping>();
        groupings.add(GroupingFactory.make(GroupingTypes.COLLECTION, "Rebellious Collection", this.session));
        groupings.add(GroupingFactory.make(GroupingTypes.SET, "Multicolor Set", this.session));

        api.addToCatalog(ClothingType.BOTTOM, "CID_fancyCrazySlacks",
                "Bottom Fancy Crazy Slacks", descriptors, groupings);

        allBottoms = session.loadAll(BottomClothing.class);
        assertEquals(before + 1, allBottoms.size());

        BottomClothing fancy_crazy_slacks = session.load(BottomClothing.class, "CID_fancyCrazySlacks");
        assertNotNull(fancy_crazy_slacks);
        assertEquals(fancy_crazy_slacks.getName(), "Bottom Fancy Crazy Slacks");
        assertEquals(fancy_crazy_slacks.getDescriptors().size(), 7);
        assertTrue(fancy_crazy_slacks.getDescriptors().containsAll(descriptors));
        assertEquals(fancy_crazy_slacks.getGroupings().size(), 2);
        assertTrue(fancy_crazy_slacks.getGroupings().containsAll(groupings));

    }

    @Test
    void recommendRelatedItems() {
        Clothing c = session.load(Clothing.class, "CID_blueJeans");
        User u = session.load(User.class, "hermione");
        List<Clothing> recommended = api.recommendRelatedItems(u.getUsername(), c);
        assertEquals(3, recommended.size());
    }

    @Test
    void recommendSimilarItems() {
        Clothing c = session.load(Clothing.class, "CID_blueJeans");
        User u = session.load(User.class, "hermione");
        List<Clothing> similarItems = api.recommendSimilarItems(u.getUsername(), c);
        assertEquals(4, similarItems.size());
    }
}
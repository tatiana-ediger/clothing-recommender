import domain.*;
import org.neo4j.ogm.session.Session;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    private static Session session;
    private static ClothingRecommenderAPI api;

    public static void main(String[] args) {
        session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        api = new ClothingRecommenderAPIIMpl(Neo4jSessionFactory.getInstance());

        switch (actionArgument(args)) {
            case "list":
                listAction(args);
                break;
            case "closet":
                closetAction(args);
                break;
            case "describe-clothing":
                describeClothingAction(args);
                break;
            case "described-by":
                describedByActions(args);
                break;
            case "grouped-by":
                groupedByActions(args);
                break;
            case "add-clothing":
                //TODO: add clothing
                break;
            case "add-to-closet":
                //TODO: add to closet
                break;
            case "recommend-purchase-related":
                //TODO: recommend related
                break;
            case "recommend-purchase-similar":
                //TODO: recommend similar
                break;
            case "recommend-purchase":
                //TODO: recommend
                break;
            case "recommend-wear-related":
                //TODO: recommend wear related
                break;
            case "recommend-wear-similar":
                //TODO: recommend wear similar
                break;
            case "recommend-war":
                //TODO: recommend wear
                break;
            default:
                System.out.println("We need a first action argument.");
                System.out.println("[list, closet, describe-clothing]");
                System.exit(1);
                break;
        }
    }

    private static void fillupDatabase() {
        session.clear();
        session.purgeDatabase();
        Descriptor jeans = DescriptorFactory.make(DescriptorType.SUBTYPE, "jeans", session);
        Descriptor slacks = DescriptorFactory.make(DescriptorType.SUBTYPE, "slacks", session);
        Descriptor shorts = DescriptorFactory.make(DescriptorType.SUBTYPE, "shorts", session);
        Descriptor skirt = DescriptorFactory.make(DescriptorType.SUBTYPE, "skirt", session);
        Descriptor sandals = DescriptorFactory.make(DescriptorType.SUBTYPE, "sandals", session);
        Descriptor suit_blazer = DescriptorFactory.make(DescriptorType.SUBTYPE, "suit_blazer", session);
        Descriptor suit_trousers = DescriptorFactory.make(DescriptorType.SUBTYPE, "suit_trousers", session);
        Descriptor dress_shoes = DescriptorFactory.make(DescriptorType.SUBTYPE, "dress_shoes", session);
        Descriptor heels = DescriptorFactory.make(DescriptorType.SUBTYPE, "heels", session);
        Descriptor button_up = DescriptorFactory.make(DescriptorType.SUBTYPE, "button_up", session);
        Descriptor blouse = DescriptorFactory.make(DescriptorType.SUBTYPE, "blouse", session);
        Descriptor top = DescriptorFactory.make(DescriptorType.SUBTYPE, "top", session);
        Descriptor sweater = DescriptorFactory.make(DescriptorType.SUBTYPE, "sweater", session);
        Descriptor t_shirt = DescriptorFactory.make(DescriptorType.SUBTYPE, "t_shirt", session);
        Descriptor red = DescriptorFactory.make(DescriptorType.COLOR, "red", session);
        Descriptor blue = DescriptorFactory.make(DescriptorType.COLOR, "blue", session);
        Descriptor black = DescriptorFactory.make(DescriptorType.COLOR, "black", session);
        Descriptor white = DescriptorFactory.make(DescriptorType.COLOR, "white", session);
        Descriptor khaki = DescriptorFactory.make(DescriptorType.COLOR, "khaki", session);
        Descriptor green = DescriptorFactory.make(DescriptorType.COLOR, "green", session);
        Descriptor gray = DescriptorFactory.make(DescriptorType.COLOR, "gray", session);
        Descriptor brown = DescriptorFactory.make(DescriptorType.COLOR, "brown", session);
        Descriptor vans = DescriptorFactory.make(DescriptorType.BRAND, "Vans", session);
        Descriptor nike = DescriptorFactory.make(DescriptorType.BRAND, "Nike", session);
        Descriptor birkenstock = DescriptorFactory.make(DescriptorType.BRAND, "Birkenstock", session);
        Descriptor fancy = DescriptorFactory.make(DescriptorType.FANCINESS, "fancy", session);
        Descriptor casual = DescriptorFactory.make(DescriptorType.FANCINESS, "casual", session);

        Grouping fall_collection = GroupingFactory.make(GroupingType.COLLECTION, "Fall 2020", session);
        Grouping winter_collection = GroupingFactory.make(GroupingType.COLLECTION, "Winter 2050", session);
        Grouping blm_collection = GroupingFactory.make(GroupingType.COLLECTION, "BLM Collection", session);
        Grouping summer_collection = GroupingFactory.make(GroupingType.COLLECTION, "Summer 1999", session);
        Grouping fancy_wedding_suit_set = GroupingFactory.make(GroupingType.SET, "Wedding Suit", session);
        Grouping fancy_party_set = GroupingFactory.make(GroupingType.SET, "Fancy Party Set", session);
        Grouping casual_blue_set = GroupingFactory.make(GroupingType.SET, "Day in the Park (blue)", session);
        Grouping casual_black_set = GroupingFactory.make(GroupingType.SET, "Day in the Park (black)", session);

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

    private static void printList(String title, Collection<Object> list, boolean div, boolean multiline) {
        StringBuilder s = new StringBuilder();
        s.append(title);
        if (multiline)
            s.append("\n");
        for (Object o : list) {
            s.append((multiline) ? "\n" : " ");
            s.append(o.toString());
            if (multiline)
                s.append("\n");
        }
        if (div)
            s.append("----------------------------------------------------\n");
        s.append("\n\n");
        System.out.print(s.toString());
    }

    private static void describedByActions(String[] args) {
        String type = getArgumentN(args, 1);
        Descriptor desc = api.loadDescriptor(type);
        printList(String.format("All clothings that are %s:", desc.getName()),
                Collections.singleton(desc.getClothings()), true, true);
    }

    private static void groupedByActions(String[] args) {
        String type = getArgumentN(args, 1);
        Grouping group = api.loadGrouping(type);
        printList(String.format("All clothings that are in %s:", group.getName()),
                Collections.singleton(group.getClothings()), true, true);
    }

    private static void describeClothingAction(String[] args) {
        String cid = getArgumentN(args, 2);
        Clothing c = api.loadClothingByCatalogID(cid);
        StringBuilder s = new StringBuilder();
        System.out.println(String.format("The clothing %s (%s) is a %s",
                c.getName(), c.getCatalogId(), c.getType().name()));
        printList("\tdescribed as: ",
                Collections.singleton(c.getDescriptors()), false, false);
        printList("\n\tgrouped as:",
                Collections.singleton(c.getGroupings()), false, false);
    }

    private static void closetAction(String[] args) {
        String username = getArgumentN(args, 2);
        User u = api.loadUserByUsername(username);
        printList(String.format("The closet for %s (%s): ", u.getName(), u.getUsername()),
                List.of(u.getCloset()), true, true);
    }

    private static void listAction(String[] args) {
        switch (listCategoryArgument(args)) {
            case "clothings":
                printList("All clothings: ", Collections.singleton(api.listClothings()), true, true);
                break;
            case "bottoms":
                printList("All bottoms: ", Collections.singleton(api.listBottoms()), true, true);
                break;
            case "tops":
                printList("All tops: ", Collections.singleton(api.listTops()), true, true);
                break;
            case "footwear":
                printList("All footwear: ", Collections.singleton(api.listFootwares()), true, true);
                break;
            case "users":
                printList("All users: ", Collections.singleton(api.listUsers()), true, true);
                break;
            case "descriptors":
                printList("All descriptors: ", Collections.singleton(api.listDescriptors()), true, true);
                break;
            case "colors":
                printList("All colors: ", Collections.singleton(api.listColorDescriptors()), true, true);
                break;
            case "brands":
                printList("All brands: ", Collections.singleton(api.listBrandDescriptors()), true, true);
                break;
            case "fanciness":
                printList("All fancy: ", Collections.singleton(api.listFancinessDescriptors()), true, true);
                break;
            case "subtypes":
                printList("All subtypes: ", Collections.singleton(api.listSubtypeDescriptors()), true, true);
                break;
            case "groupings":
                printList("All groupings: ", Collections.singleton(api.listGroupings()), true, true);
                break;
            case "collections":
                printList("All collections: ", Collections.singleton(api.listCollectionGroupings()), true, true);
                break;
            case "sets":
                printList("All sets: ", Collections.singleton(api.listSetGroupings()), true, true);
                break;
            default:
                System.out.println("We need a second argument for list category.");
                System.out.println("[clothings, bottoms, tops, footwear, users]");
                System.exit(1);
                break;
        }
    }

    private static String listCategoryArgument(String[] args) {
        return getArgumentN(args, 1);
    }

    private static String actionArgument(String[] args) {
        return getArgumentN(args, 0);
    }

    private static String getArgumentN(String[] args, int n) {
        return (args.length > 0) ? args[0] : null;
    }
}
import domain.Descriptor;
import domain.Clothing;
import domain.ColorDescriptor;
import domain.TopClothing;
import org.neo4j.ogm.session.Session;


public class Main {

    public static void main(String... args) throws Exception {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

        Clothing yellowshirt = new TopClothing(8, "Yellow Shirt");
        Descriptor yellow = new ColorDescriptor("yellow");

        yellowshirt.addAttribute(yellow);
        session.save(yellowshirt);

    }
}
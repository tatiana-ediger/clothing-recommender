import domain.Clothing;
import domain.ColorDescriptor;
import domain.Descriptor;
import domain.TopClothing;
import org.neo4j.ogm.session.Session;


public class Main {

    public static void main(String... args) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

        Clothing s1 = new TopClothing("Yellow Shirt");
        Descriptor yellow = new ColorDescriptor("yellow");

        s1.addAttribute(yellow);
        session.save(s1);

    }
}
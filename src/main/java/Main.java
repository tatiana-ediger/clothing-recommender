import domain.Clothing;
import org.neo4j.ogm.session.Session;


public class Main {

    public static void main(String... args) throws Exception {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

        Clothing whiteshirt = new Clothing(8, "A White Shirt");

        session.save(whiteshirt);

    }
}
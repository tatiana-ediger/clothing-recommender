import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Neo4jSessionFactory {

    private static final Neo4jSessionFactory factory = new Neo4jSessionFactory();
    private final SessionFactory sessionFactory;
    private final Configuration config;

    // prevent external instantiation
    private Neo4jSessionFactory() {
        this.config = new Configuration.Builder()
                .uri("bolt://localhost:7687")
                .credentials("neo4j", "otherpassword")
                .verifyConnection(true)
                //.database("TestProjectGraph")
                .useNativeTypes()
                .withBasePackages("domain")
                .build();
        this.sessionFactory = new SessionFactory(this.config, "domain");
    }

    public static Neo4jSessionFactory getInstance() {
        return factory;
    }

    public Session getNeo4jSession() {
        return this.sessionFactory.openSession();
    }
}
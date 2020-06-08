import domain.Attribute;
import domain.AttributeTypes;
import domain.ColorAttribute;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;

import java.util.Collection;

public class AttributeFactory {

    private Session session;

    public AttributeFactory(Neo4jSessionFactory sessionFactory) {
        this.session = sessionFactory.getNeo4jSession();
    }

    public Attribute make(AttributeTypes type, String value) {
        switch (type) {
            case COLOR:
                // We should never have more than one
                ColorAttribute previous = session.load(ColorAttribute.class, value);
                if (previous == null) {
                    return new ColorAttribute(value);
                }
                return previous;
        };
        throw new IllegalArgumentException("This is not a valid type.");
    }
}

import domain.*;
import org.neo4j.ogm.session.Session;

/**
 * A class to create an Attribute.
 */
public class DescriptorFactory {

    private final Session session;

    public DescriptorFactory(Neo4jSessionFactory sessionFactory) {
        this.session = sessionFactory.getNeo4jSession();
    }

    /**
     * Makes an instance of an attribute with the given type and value if it does not already exists
     * or retrieves it if it already does.
     *
     * @param type  the given type of Attribute.
     * @param value the value of the given Attribute.
     * @return the Attribute.
     */
    public static Descriptor make(DescriptorType type, String value, Session session) {
        Descriptor prev;
        Descriptor desc = null;
        switch (type) {
            case COLOR:
                // We should never have more than one
                prev = session.load(ColorDescriptor.class, value);
                if (prev == null)
                    desc = new ColorDescriptor(value);
                break;
            case BRAND:
                prev = session.load(BrandDescriptor.class, value);
                if (prev == null)
                    desc = new BrandDescriptor(value);
                break;
            case SUBTYPE:
                prev = session.load(SubtypeDescriptor.class, value);
                if (prev == null)
                    desc = new SubtypeDescriptor(value);
                break;
            case FANCINESS:
                prev = session.load(FancinessDescriptor.class, value);
                if (prev == null)
                    desc = new FancinessDescriptor(value);
                break;
            default:
                throw new IllegalArgumentException("This is not a valid type.");
        }
        if (prev != null) {
            return prev;
        } else {
            session.save(desc);
            return desc;
        }
    }
}

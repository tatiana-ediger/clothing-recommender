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
    public static Descriptor make(DescriptorTypes type, String value, Session session) {
        switch (type) {
            case COLOR:
                // We should never have more than one
                ColorDescriptor previous = session.load(ColorDescriptor.class, value);
                if (previous == null) {
                    Descriptor desc = new ColorDescriptor(value);
                    session.save(desc);
                    return desc;
                }
                return previous;
            case BRAND:
                BrandDescriptor previousBrand = session.load(BrandDescriptor.class, value);
                if (previousBrand == null) {
                    Descriptor desc = new BrandDescriptor(value);
                    session.save(desc);
                    return desc;
                }
                return previousBrand;
            case SUBTYPE:
                SubtypeDescriptor previousSubtype = session.load(SubtypeDescriptor.class, value);
                if (previousSubtype == null) {
                    Descriptor desc = new SubtypeDescriptor(value);
                    session.save(desc);
                    return desc;
                }
                return previousSubtype;
            case FANCINESS:
                FancinessDescriptor previousFancy = session.load(FancinessDescriptor.class, value);
                if (previousFancy == null) {
                    Descriptor desc = new FancinessDescriptor(value);
                    session.save(desc);
                    return desc;
                }
                return previousFancy;
            default:
                throw new IllegalArgumentException("This is not a valid type.");
        }
    }
}

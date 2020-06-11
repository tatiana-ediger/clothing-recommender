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
    public Descriptor make(DescriptorTypes type, String value) {
        switch (type) {
            case COLOR:
                // We should never have more than one
                ColorDescriptor previous = this.session.load(ColorDescriptor.class, value);
                if (previous == null) {
                    return new ColorDescriptor(value);
                }
                return previous;
            case BRAND:
                BrandDescriptor previousBrand = this.session.load(BrandDescriptor.class, value);
                if (previousBrand == null) {
                    return new BrandDescriptor(value);
                }
                return previousBrand;
            case SUBTYPE:
                SubtypeDescriptor previousSubtype = this.session.load(SubtypeDescriptor.class, value);
                if (previousSubtype == null) {
                    return new SubtypeDescriptor(value);
                }
                return previousSubtype;
            case FANCINESS:
                FancinessDescriptor previousFancy = this.session.load(FancinessDescriptor.class, value);
                if (previousFancy == null) {
                    return new FancinessDescriptor(value);
                }
                return previousFancy;
            default:
                throw new IllegalArgumentException("This is not a valid type.");
        }
    }
}

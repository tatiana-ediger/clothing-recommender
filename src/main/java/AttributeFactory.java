import domain.*;
import org.neo4j.ogm.session.Session;

/**
 * A class to create an Attribute.
 */
public class AttributeFactory {

    private Session session;

    public AttributeFactory(Neo4jSessionFactory sessionFactory) {
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
    public Attribute make(AttributeTypes type, String value) {
        switch (type) {
            case COLOR:
                // We should never have more than one
                ColorAttribute previous = session.load(ColorAttribute.class, value);
                if (previous == null) {
                    return new ColorAttribute(value);
                }
                return previous;
            case BRAND:
                BrandAttribute previousBrand = session.load(BrandAttribute.class, value);
                if (previousBrand == null) {
                    return new BrandAttribute(value);
                }
                return previousBrand;
            case SUBTYPE:
                SubtypeAttribute previousSubtype = session.load(SubtypeAttribute.class, value);
                if (previousSubtype == null) {
                    return new SubtypeAttribute(value);
                }
                return previousSubtype;
            case FANCINESS:
                FancinessAttribute previousFancy = session.load(FancinessAttribute.class, value);
                if (previousFancy == null) {
                    return new FancinessAttribute(value);
                }
                return previousFancy;
            default:
                throw new IllegalArgumentException("This is not a valid type.");
        }
    }
}

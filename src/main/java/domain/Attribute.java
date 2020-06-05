package domain;

import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class Attribute extends AEntity {

    //@Property(name = "name")
    private String name;
    //@Property(name = "value")
    private String value;
    @Relationship(type = "CLOTHING_ATTRIBUTE", direction = Relationship.OUTGOING)
    private Set<Clothing> clothings;

    public Attribute() {
    }

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
        this.clothings = new HashSet<>();
    }
}
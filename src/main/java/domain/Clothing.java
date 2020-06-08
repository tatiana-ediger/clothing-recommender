package domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public abstract class Clothing extends AEntity {

    @Property(name = "user")

    private long userID;
    @Property(name = "name")
    private String name;

    @Relationship(type = "CLOTHING_ATTRIBUTE", direction = Relationship.INCOMING)
    private Set<Attribute> attributes;

    public Clothing() {
    }

    public Clothing(long userID, String name) {
        this(userID, name, new HashSet<>());
    }

    Clothing(long userID, String name, Set<Attribute> attributes) {
        this.userID = userID;
        this.name = name;
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }
}


package domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Clothing extends AEntity {

    @Property(name = "user")

    private int userID;
    @Property(name = "name")
    private String name;

    @Relationship(type = "CLOTHING_ATTRIBUTE", direction = Relationship.INCOMING)
    private Set<Attribute> attributes;

    public Clothing() {
    }

    public Clothing(int userID, String name) {
        this(userID, name, new HashSet<>());
    }

    Clothing(int userID, String name, Set<Attribute> attributes) {
        this.userID = userID;
        this.name = name;
        this.attributes = attributes;
    }
//
//    void addAttribute(Attribute attribute, String value) {
//        //this.attributes.put(attribute, value);
//    }
}


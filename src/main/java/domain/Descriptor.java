package domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity()
public abstract class Descriptor {

    @Id()
    private String value;

    @Relationship(type = "CLOTHING_ATTRIBUTE", direction = Relationship.OUTGOING)
    private Set<Clothing> clothings;

    public Descriptor() {
    }

    public Descriptor(String value) {
        this.value = value;
        this.clothings = new HashSet<>();
    }
}
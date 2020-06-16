package domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity()
public abstract class Grouping {
    @Id()
    private String value;

    @Relationship(type = "CLOTHING_GROUPING", direction = Relationship.OUTGOING)
    private Set<Clothing> clothings;

    public Grouping() {
    }

    public Grouping(String value) {
        this.value = value;
        this.clothings = new HashSet<>();
    }

    public Set<Clothing> getClothings() {
        return this.clothings;
    }

    public void addClothing(Clothing clothing) {
        this.clothings.add(clothing);
    }
}

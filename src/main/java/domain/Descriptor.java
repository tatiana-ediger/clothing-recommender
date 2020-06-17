package domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity()
public abstract class Descriptor {

    @Id()
    @Index(unique = true)
    private String value;

    @Relationship(type = "CLOTHING_DESCRIPTOR", direction = Relationship.OUTGOING)
    private Set<Clothing> clothings;

    public Descriptor() {
    }

    public Descriptor(String value) {
        this.value = value;
        this.clothings = new HashSet<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Descriptor)) return false;
        Descriptor that = (Descriptor) o;
        return this.value.equals(that.value);
    }

    public String getName() {
        return this.value;
    }

    public Set<Clothing> getClothings() {
        return this.clothings; //TODO: abstract with grouping??
    }

    public void addClothing(Clothing clothing) {
        this.clothings.add(clothing);
    }
}
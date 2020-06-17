package domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity()
public abstract class Grouping {

    @Id()
    @Index(unique = true)
    protected String value;

    @Relationship(type = "CLOTHING_GROUPING", direction = Relationship.OUTGOING)
    protected Set<Clothing> clothings;

    public Grouping() {
    }

    public Grouping(String value) {
        this.value = value;
        this.clothings = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Grouping{" +
                "value='" + this.value + '\'' +
                ", clothings=" + this.clothings.size() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grouping)) return false;
        Grouping that = (Grouping) o;
        return this.value.equals(that.value);
    }

    public String getName() {
        return this.value;
    }

    public Set<Clothing> getClothings() {
        return this.clothings;
    }

    public void addClothing(Clothing clothing) {
        this.clothings.add(clothing);
    }
}

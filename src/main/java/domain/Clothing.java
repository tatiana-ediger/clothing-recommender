package domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public abstract class Clothing extends AEntity {

    @Property(name = "name")
    private String name;

    @Relationship(type = "CLOTHING_DESCRIPTOR", direction = Relationship.INCOMING)
    private Set<Descriptor> descriptors;

    @Relationship(type = "CLOTHING_GROUPING", direction = Relationship.INCOMING)
    private Set<Grouping> groupings;

    @Relationship(type = "USER_CLOTHING", direction = Relationship.INCOMING)
    private Set<User> users;

    public Clothing() {
    }

    public Clothing(String name) {
        this(name, new HashSet<>());
    }

    Clothing(String name, Set<Descriptor> descriptors) {
        this.name = name;
        this.descriptors = descriptors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getName(), this.getDescriptors(), this.getUsers());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Clothing)) return false;
        if (! super.equals(o)) return false;
        Clothing clothing = (Clothing) o;
        return Objects.equals(this.getName(), clothing.getName()) &&
                Objects.equals(this.getDescriptors(), clothing.getDescriptors()) &&
                Objects.equals(this.getUsers(), clothing.getUsers());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Descriptor> getDescriptors() {
        return this.descriptors;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public Set<Grouping> getGroupings() {
        return this.groupings;
    }

    public void addDescriptors(List<Descriptor> attrs) {
        this.descriptors.addAll(attrs);
    }

    public void addDescriptor(Descriptor descriptor) {
        this.descriptors.add(descriptor);
    }

    public void addGroupings(List<Grouping> attrs) {
        this.groupings.addAll(attrs);
    }

    public void addGrouping(Grouping grouping) {
        this.groupings.add(grouping);
    }
}


package domain;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity
public abstract class Clothing extends AEntity {

    @Relationship(type = "CLOTHING_DESCRIPTOR", direction = Relationship.INCOMING)
    private final Set<Descriptor> descriptors;
    @Relationship(type = "CLOTHING_GROUPING", direction = Relationship.INCOMING)
    private final Set<Grouping> groupings;
    @Relationship(type = "Owns", direction = Relationship.INCOMING)
    private final Set<User> users;

    @Index(unique = true)
    @Id()
    @Property(name = "catalogID")
    private String catalogID;
    @Property(name = "name")
    private String name;

    public Clothing() {
        this(new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    public Clothing(String catalogID, String name) {
        this(catalogID, name, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    Clothing(Set<Descriptor> descriptors, Set<Grouping> groupings, Set<User> users) {
        this.descriptors = descriptors;
        this.groupings = groupings;
        this.users = users;
    }

    Clothing(String catalogID, String name, Set<Descriptor> descriptors, Set<Grouping> groupings, Set<User> users) {
        this(descriptors, groupings, users);
        this.catalogID = catalogID;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.catalogID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clothing)) return false;
        Clothing other = (Clothing) o;
        return Objects.equals(this.getCatalogId(), other.getCatalogId());
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

    public String getCatalogId() {
        return this.catalogID;
    }

    public void addDescriptors(List<Descriptor> attrs) {
        attrs.forEach(this::addDescriptor);
    }

    public void addDescriptor(Descriptor descriptor) {
        this.descriptors.add(descriptor);
        descriptor.addClothing(this);
    }

    public void addGroupings(List<Grouping> attrs) {
        attrs.forEach(this::addGrouping);
    }

    public void addGrouping(Grouping grouping) {
        this.groupings.add(grouping);
        grouping.addClothing(this);
    }

    public abstract ClothingType getType();

    public boolean matchesType(Clothing other) {
        return this.getType() == other.getType();
    }

    public List<Clothing> filterOfDifferentType(List<Clothing> clothings) {
        return clothings.stream().filter(clothing -> clothing.matchesType(this)).collect(Collectors.toList());
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}


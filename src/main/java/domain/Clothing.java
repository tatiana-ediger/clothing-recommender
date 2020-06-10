package domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public abstract class Clothing {

    @Id()
    @Property(name = "name")
    private String name;

    @Relationship(type = "CLOTHING_ATTRIBUTE", direction = Relationship.INCOMING)
    private Set<Descriptor> descriptors;

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

    public void addAttribute(Descriptor descriptor) {
        this.descriptors.add(descriptor);
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

    public void setDescriptors(Set<Descriptor> descriptors) {
        this.descriptors = descriptors;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}


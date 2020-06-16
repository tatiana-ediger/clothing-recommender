package domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User extends AEntity {

    @Relationship(type = "Owns", direction = Relationship.OUTGOING)
    private final Set<Clothing> closet; //Cloths + Set
    @Id
    @Property(name = "username")
    private String username;
    @Property(name = "name")
    private String name;

    public User() {
        this.closet = new HashSet<>();
    }

    public User(String username, String name) {
        this(name, new HashSet<>());
    }

    public User(String name, Long id) {
        super(id);
        this.name = name;
        this.closet = new HashSet<>();
    }

    User(String name, Set<Clothing> closet) {
        this.name = name;
        this.closet = closet;
    }

    public void addToCloset(Clothing clothing) {
        this.closet.add(clothing);
        clothing.addUser(this);
    }
}

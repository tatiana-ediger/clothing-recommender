package domain;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User extends AEntity {

    @Relationship(type = "Owns", direction = Relationship.OUTGOING)
    private final Set<Clothing> closet; //Cloths + Set

    @Index(unique = true)
    @Id()
    @Property(name = "username")
    private String username;
    @Property(name = "name")
    private String name;

    public User() {
        this.closet = new HashSet<>();
    }

    User(String username, String name, Set<Clothing> closet) {
        this.username = username;
        this.name = name;
        this.closet = closet;
    }

    public User(String username, String name) {
        this(username, name, new HashSet<>());
    }

    public void addToCloset(Clothing clothing) {
        this.closet.add(clothing);
        clothing.addUser(this);
    }

    public Set<Clothing> getCloset() {
        return this.closet;
    }

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "User{" +
                "closet=" + this.closet.size() +
                ", username='" + this.username + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }
}

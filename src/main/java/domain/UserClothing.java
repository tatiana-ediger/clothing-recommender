package domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "Owns")
public class UserClothing extends ARelationship {

    @StartNode
    private User user;
    @EndNode
    private Clothing clothing;

    @Override
    public String toString() {
        return "UserClothing{" +
                "user=" + this.user.getUsername() +
                ", clothing=" + this.clothing.getCatalogId() +
                '}';
    }
}
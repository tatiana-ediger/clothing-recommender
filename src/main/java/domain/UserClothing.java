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
}
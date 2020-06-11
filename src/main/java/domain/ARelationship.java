package domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

public abstract class ARelationship {

    @Id
    @GeneratedValue
    private Long relationshipId;

}

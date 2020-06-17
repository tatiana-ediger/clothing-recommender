package domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

public abstract class ARelationship {

    @Id
    @GeneratedValue
    protected Long relationshipId;

    @Override
    public String toString() {
        return "ARelationship{" +
                "relationshipId=" + this.relationshipId +
                '}';
    }

    public Long getRelationshipId() {
        return this.relationshipId;
    }
}

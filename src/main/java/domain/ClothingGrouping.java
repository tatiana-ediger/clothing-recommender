package domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity()
public class ClothingGrouping extends ARelationship {
    @StartNode
    private Grouping grouping;
    @EndNode
    private Clothing clothing;

    @Override
    public String toString() {
        return "ClothingGrouping{" +
                "grouping=" + this.grouping.getName() +
                ", clothing=" + this.clothing.getCatalogId() +
                '}';
    }
}

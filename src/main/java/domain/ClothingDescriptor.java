package domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.StartNode;

//@RelationshipEntity(type = "CLOTHING_DESCRIPTOR")
public class ClothingDescriptor extends ARelationship {

    @StartNode
    private Descriptor descriptor;
    @EndNode
    private Clothing clothing;

    @Override
    public String toString() {
        return "ClothingDescriptor{" +
                "descriptor=" + this.descriptor.getName() +
                ", clothing=" + this.clothing.getCatalogId() +
                '}';
    }
}

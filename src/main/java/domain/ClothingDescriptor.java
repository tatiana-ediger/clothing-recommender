package domain;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity()
public class ClothingDescriptor {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @StartNode
    private Descriptor descriptor;

    @EndNode
    private Clothing clothing;
}

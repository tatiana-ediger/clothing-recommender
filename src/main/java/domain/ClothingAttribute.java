package domain;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity()
public class ClothingAttribute {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @StartNode
    private Attribute attribute;

    @EndNode
    private Clothing clothing;
}

package domain;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity()

public class ClothingGrouping {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @StartNode
    private Grouping descriptor;

    @EndNode
    private Clothing clothing;
}

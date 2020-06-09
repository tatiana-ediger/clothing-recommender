package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Collection")
public class CollectionGrouping extends Grouping {

    public CollectionGrouping() {
    }

    public CollectionGrouping(String value) {
        super(value);
    }
}

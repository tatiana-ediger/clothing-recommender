package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Collection")
public class CollectionGrouping extends Grouping {

    public CollectionGrouping() {
    }

    public CollectionGrouping(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "CollectionGrouping{" +
                "value='" + this.value + '\'' +
                ", clothings=" + this.clothings.size() +
                '}';
    }
}

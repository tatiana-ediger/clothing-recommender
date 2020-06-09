package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Subtype")
public class SubtypeAttribute extends Attribute {

    public SubtypeAttribute() {

    }

    public SubtypeAttribute(String value) {
        super(value);
    }
}

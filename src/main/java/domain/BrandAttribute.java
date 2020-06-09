package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Brand")
public class BrandAttribute extends Attribute {

    public BrandAttribute() {

    }

    public BrandAttribute(String value) {
        super(value);
    }
}

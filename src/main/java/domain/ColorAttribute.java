package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Color")
public class ColorAttribute extends Attribute {

    public ColorAttribute() {

    }

    public ColorAttribute(String value) {
        super(value);
    }

}

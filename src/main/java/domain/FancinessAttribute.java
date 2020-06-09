package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Fanciness")
public class FancinessAttribute extends Attribute {

    public FancinessAttribute() {

    }

    public FancinessAttribute(String value) {
        super(value);
    }
}

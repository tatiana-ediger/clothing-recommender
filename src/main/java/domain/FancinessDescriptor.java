package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Fanciness")
public class FancinessDescriptor extends Descriptor {

    public FancinessDescriptor() {

    }

    public FancinessDescriptor(String value) {
        super(value);
    }
}

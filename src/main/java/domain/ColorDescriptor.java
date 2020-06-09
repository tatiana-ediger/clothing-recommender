package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Color")
public class ColorDescriptor extends Descriptor {

    public ColorDescriptor() {

    }

    public ColorDescriptor(String value) {
        super(value);
    }

}

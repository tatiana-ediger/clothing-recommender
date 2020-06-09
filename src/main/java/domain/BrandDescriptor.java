package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Brand")
public class BrandDescriptor extends Descriptor {

    public BrandDescriptor() {

    }

    public BrandDescriptor(String value) {
        super(value);
    }
}

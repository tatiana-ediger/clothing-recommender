package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Subtype")
public class SubtypeDescriptor extends Descriptor {

    public SubtypeDescriptor() {

    }

    public SubtypeDescriptor(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "SubtypeDescriptor{" +
                "value='" + this.value + '\'' +
                ", clothings=" + this.clothings.size() +
                '}';
    }
}

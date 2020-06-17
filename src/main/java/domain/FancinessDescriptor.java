package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Fanciness")
public class FancinessDescriptor extends Descriptor {

    public FancinessDescriptor() {

    }

    public FancinessDescriptor(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "FancinessDescriptor{" +
                "value='" + this.value + '\'' +
                ", clothings=" + this.clothings.size() +
                '}';
    }
}

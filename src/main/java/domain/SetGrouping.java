package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Set")
public class SetGrouping extends Grouping {

    public SetGrouping() {
    }

    public SetGrouping(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "SetGrouping{" +
                "value='" + this.value + '\'' +
                ", clothings=" + this.clothings.size() +
                '}';
    }
}

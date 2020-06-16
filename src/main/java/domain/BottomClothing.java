package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Bottom")
public class BottomClothing extends Clothing {

    public BottomClothing() {

    }

    public BottomClothing(String name) {
        super(name);
    }

    @Override
    public ClothingType getType() {
        return ClothingType.BOTTOM;
    }
}

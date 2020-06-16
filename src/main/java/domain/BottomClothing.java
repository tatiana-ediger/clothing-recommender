package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Bottom")
public class BottomClothing extends Clothing {

    public BottomClothing() {

    }

    public BottomClothing(String catalogID, String name) {
        super(catalogID, name);
    }

    @Override
    public ClothingType getType() {
        return ClothingType.BOTTOM;
    }
}

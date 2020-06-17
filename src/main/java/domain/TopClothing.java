package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Top")
public class TopClothing extends Clothing {

    public TopClothing() {

    }

    public TopClothing(String catalogID, String name) {
        super(catalogID, name);
    }

    @Override
    public ClothingType getType() {
        return ClothingType.TOP;
    }
}

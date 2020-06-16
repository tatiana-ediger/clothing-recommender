package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Footwear")
public class FootwearClothing extends Clothing {

    public FootwearClothing() {

    }

    public FootwearClothing(String name) {
        super(name);
    }

    @Override
    public ClothingType getType() {
        return ClothingType.FOOTWEAR;
    }
}

package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Footwear")
public class FootwearClothing extends Clothing {

    public FootwearClothing() {

    }

    public FootwearClothing(String catalogID, String name) {
        super(catalogID, name);
    }

    @Override
    public String toString() {
        return "FootwearClothing{" +
                "descriptors=" + this.descriptors.size() +
                ", groupings=" + this.groupings.size() +
                ", users=" + this.users.size() +
                ", catalogID='" + this.catalogID + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }

    @Override
    public ClothingType getType() {
        return ClothingType.FOOTWEAR;
    }
}

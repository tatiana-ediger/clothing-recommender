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
    public String toString() {
        return "BottomClothing{" +
                "descriptors=" + this.descriptors.size() +
                ", groupings=" + this.groupings.size() +
                ", users=" + this.users.size() +
                ", catalogID='" + this.catalogID + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }

    @Override
    public ClothingType getType() {
        return ClothingType.BOTTOM;
    }
}

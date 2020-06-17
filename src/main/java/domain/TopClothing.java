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

    @Override
    public String toString() {
        return "TopClothing{" +
                "descriptors=" + this.descriptors.size() +
                ", groupings=" + this.groupings.size() +
                ", users=" + this.users.size() +
                ", catalogID='" + this.catalogID + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }
}

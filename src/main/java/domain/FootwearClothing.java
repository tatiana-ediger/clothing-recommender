package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Footwear")
public class FootwearClothing extends Clothing {

    public FootwearClothing() {

    }

    public FootwearClothing(long userID, String name) {
        super(userID, name);
    }
}

package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Bottom")
public class BottomClothing extends Clothing {

    public BottomClothing() {

    }

    public BottomClothing(long userID, String name) {
        super(userID, name);
    }
}

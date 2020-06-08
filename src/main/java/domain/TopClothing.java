package domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Top")
public class TopClothing extends Clothing {

    public TopClothing() {

    }

    public TopClothing(long userID, String name) {
        super(userID, name);
    }
}

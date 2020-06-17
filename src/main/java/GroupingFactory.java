import domain.CollectionGrouping;
import domain.Grouping;
import domain.GroupingType;
import domain.SetGrouping;
import org.neo4j.ogm.session.Session;

public class GroupingFactory {
    public static Grouping make(GroupingType type, String value, Session session) {
        Grouping prev;
        Grouping grouping = null;
        switch (type) {
            case COLLECTION:
                prev = session.load(CollectionGrouping.class, value); //TODO: test!!!
                if (prev == null)
                    grouping = new CollectionGrouping(value);
                break;
            case SET:
                prev = session.load(SetGrouping.class, value);
                if (prev == null)
                    grouping = new SetGrouping(value);
                break;
            default:
                throw new IllegalArgumentException("Grouping type not found");
        }
        if (prev != null) {
            return prev;
        } else {
            session.save(grouping);
            return grouping;
        }
    }
}

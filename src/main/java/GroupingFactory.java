import domain.CollectionGrouping;
import domain.Grouping;
import domain.GroupingTypes;
import domain.SetGrouping;

public class GroupingFactory {
    public static Grouping make(GroupingTypes type, String name) {
        switch (type) {
            case COLLECTION:
                return new CollectionGrouping(name);
            case SET:
                return new SetGrouping(name);
            default:
                throw new IllegalArgumentException("Grouping type not found");
        }
    }
}

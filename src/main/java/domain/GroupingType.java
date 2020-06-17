package domain;

public enum GroupingType {

    COLLECTION("collection"), SET("set");

    private final String type;

    GroupingType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}

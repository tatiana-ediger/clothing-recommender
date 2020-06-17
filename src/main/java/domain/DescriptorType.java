package domain;

public enum DescriptorType {

    COLOR("color"), BRAND("brand"), SUBTYPE("subtype"), FANCINESS("fanciness");

    private final String type;

    DescriptorType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}

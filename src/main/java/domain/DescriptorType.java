package domain;

public enum DescriptorType {

    COLOR("color"), BRAND("brand"), SUBTYPE("subtype"), FANCINESS("fanciness");

    private final String type;

    DescriptorType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DescriptorType{" +
                "type='" + this.type + '\'' +
                '}';
    }

    public String getType() {
        return this.type;
    }
}

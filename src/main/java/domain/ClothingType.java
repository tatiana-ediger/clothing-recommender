package domain;

public enum ClothingType {

    TOP("Top"), BOTTOM("Bottom"), FOOTWEAR("Footwear");

    private final String type;

    ClothingType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClothingType{" +
                "type='" + this.type + '\'' +
                '}';
    }

    public String getType() {
        return this.type;
    }
}

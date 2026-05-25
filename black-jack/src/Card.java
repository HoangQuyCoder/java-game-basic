public class Card {
    private final String value;
    private final String type;

    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String getValueString() {
        return value;
    }

    public String getTypeString() {
        return type;
    }

    @Override
    public String toString() {
        return value + "-" + type;
    }

    public int getValue() {
        if ("AJQK".contains(value)) {
            if (value.equals("A")) {
                return 11;
            }
            return 10;
        } else {
            return Integer.parseInt(value);
        }
    }

    public boolean isAce() {
        return value.equals("A");
    }
}

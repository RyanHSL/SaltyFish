package com.saltyFish.appointment.lookups;

public enum Attributes {
    TIME(1, "time"),
    SERVICE(2, "service"),
    SCORE(3, "score");

    private final String symbol;

    Attributes(int id, String symbol) {
        this.symbol = symbol;
    }

    public String getAttributeSymbol() {
        return symbol;
    }

    public static Attributes fromSymbol(String symbol) {
        for (Attributes attribute: Attributes.values()) {
            if (attribute.getAttributeSymbol().equals(symbol)) {
                return attribute;
            }
        }
        throw new IllegalArgumentException("Unknown operator symbol: " + symbol);
    }

}

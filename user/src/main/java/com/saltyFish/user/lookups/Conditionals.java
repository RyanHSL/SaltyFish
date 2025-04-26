package com.saltyFish.user.lookups;

public enum Conditionals {
    EQUALS(1, "=="),
    NOT_EQUALS(2, "!="),
    CONTAINS(3, "contains"),
    GREATER_THAN(4, ">"),
    LESS_THAN(5, "<"),
    GREATER_OR_EQUAL(6, ">="),
    LESS_OR_EQUAL(7, "<=");

    private final int id;
    private final String symbol;

    Conditionals(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public String getConditionalSymbol() {
        return symbol;
    }

    public static Conditionals fromSymbol(String symbol){
        for (Conditionals conditional: Conditionals.values()) {
            if (conditional.getConditionalSymbol().equals(symbol)) {
                return conditional;
            }
        }
        throw new IllegalArgumentException("Unknown operator symbol: " + symbol);
    }
}

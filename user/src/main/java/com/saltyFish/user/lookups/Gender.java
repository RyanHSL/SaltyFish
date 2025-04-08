package com.saltyFish.user.lookups;

import java.util.HashMap;
import java.util.Map;

public enum Gender {

    MALE(1, "Male"),
    FEMALE(2, "Female"),
    OTHER(3, "Other");

    private final int id;
    private final String description;

    private static final Map<Integer, Gender> idMap = new HashMap<>();

    static {
        for (Gender gender: Gender.values()) {
            idMap.put(gender.id, gender);
        }
    }

    Gender(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Gender formId(int id) {
        return idMap.get(id);
    }
}

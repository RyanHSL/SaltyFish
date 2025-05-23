package com.saltyFish.appointment.lookups;

import java.util.HashMap;
import java.util.Map;

public enum CancellationReason {

    CONFLICT(1, "Conflict"),
    UNCOMFORTABLE(2, "Uncomfortable"),
    NO_LONGER_NEEDED(3, "No Longer Needed"),
    OTHER(4, "Other");

    private final int id;
    private final String description;

    private static final Map<Integer, CancellationReason> idMap = new HashMap<>();

    static {
        for (CancellationReason reason: CancellationReason.values()) {
            idMap.put(reason.id, reason);
        }
    }

    CancellationReason(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static CancellationReason formId(int id) {
        return idMap.get(id);
    }
}

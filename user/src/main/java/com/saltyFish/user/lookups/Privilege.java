package com.saltyFish.user.lookups;

import java.util.HashMap;
import java.util.Map;

public enum Privilege {

    VIEW(1, "View Service"),
    REQUEST(2, "Request Service"),
    POST_REVIEW(3, "Post Review"),
    DELETE_REVIEW(4, "Delete Review"),
    CANCEL(5, "Cancel Appointment And Booking"),
    CONFIRM(6, "Confirm Appointment"),
    REJECT(7, "Reject Booking"),
    CREATE_SERVICE(8, "Create Service"),
    DELETE_SERVICE(9, "Delete Service"),
    PROMOTED_REQUEST(10, "Promoted Request"),
    PROMOTED_SERVICE(11, "Promoted Service");

    private final int id;
    private final String description;

    private static final Map<Integer, Privilege> idMap = new HashMap<>();

    static {
        for (Privilege privilege: Privilege.values()) {
            idMap.put(privilege.id, privilege);
        }
    }

    Privilege(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Privilege formId(int id) {
        return idMap.get(id);
    }
}

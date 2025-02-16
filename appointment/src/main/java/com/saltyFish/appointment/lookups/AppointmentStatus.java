package com.saltyFish.appointment.lookups;

import java.util.HashMap;
import java.util.Map;

public enum AppointmentStatus {

    CREATED(1, "Appointment Created"),
    IN_PROGRESS(2, "Appointment In Progress"),
    PENDING_APPROVAL_FROM_PROVIDER(3, "Pending Approval From The Provider"),
    PENDING_APPROVAL_FROM_REQUESTER(4, "Pending Approval From The Requester"),
    COMPLETED(5, "Appointment Completed"),
    CANCELLED(6, "Appointment Cancelled");

    private final int id;
    private final String description;

    private static final Map<Integer, AppointmentStatus> idMap = new HashMap<>();

    static {
        for (AppointmentStatus status: AppointmentStatus.values()) {
            idMap.put(status.id, status);
        }
    }

    AppointmentStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static AppointmentStatus formId(int id) {
        return idMap.get(id);
    }
}

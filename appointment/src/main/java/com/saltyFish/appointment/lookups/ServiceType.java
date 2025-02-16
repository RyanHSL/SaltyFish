package com.saltyFish.appointment.lookups;

import java.util.HashMap;
import java.util.Map;

public enum ServiceType {

    USED_ITEMS(1, "USED ITEMS"),
    CLEANING_SERVICES(2, "CLEANING SERVICS"),
    RIDE(3, "RIDE"),
    TICKET_RESALE(4, "TICKET RESALE");

    private final int id;
    private final String description;

    private static final Map<Integer, ServiceType> idMap = new HashMap<>();

    static {
        for (ServiceType service: ServiceType.values()) {
            idMap.put(service.id, service);
        }
    }

    ServiceType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static ServiceType formId(int id) {
        return idMap.get(id);
    }
}

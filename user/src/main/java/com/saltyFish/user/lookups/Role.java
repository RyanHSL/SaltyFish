package com.saltyFish.user.lookups;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Role {

    REQUESTER(1, "Requester", Set.of(Privilege.VIEW, Privilege.REQUEST, Privilege.POST_REVIEW, Privilege.DELETE_REVIEW, Privilege.CANCEL)),
    ADMIN(2, "Admin", Set.of(Privilege.VIEW, Privilege.REQUEST, Privilege.POST_REVIEW, Privilege.DELETE_REVIEW, Privilege.CANCEL, Privilege.CONFIRM, Privilege.REJECT, Privilege.CREATE_SERVICE, Privilege.DELETE_SERVICE, Privilege.PROMOTED_REQUEST, Privilege.PROMOTED_SERVICE)),
    SERVICE_PROVIDER(3, "Service Provider", Set.of(Privilege.VIEW, Privilege.REQUEST, Privilege.POST_REVIEW, Privilege.DELETE_REVIEW, Privilege.CANCEL, Privilege.CONFIRM, Privilege.REJECT)),
    MEMBERED_REQUESTER(4, "Membered Requester", Set.of(Privilege.VIEW, Privilege.REQUEST, Privilege.POST_REVIEW, Privilege.DELETE_REVIEW, Privilege.CANCEL, Privilege.PROMOTED_REQUEST)),
    MEMBERED_SERVICE_PROVIDER(5, "Membered Service Provider", Set.of(Privilege.VIEW, Privilege.REQUEST, Privilege.POST_REVIEW, Privilege.DELETE_REVIEW, Privilege.CANCEL, Privilege.PROMOTED_SERVICE));

    private final int id;
    private final String description;
    private final Set<Privilege> privileges;

    private static final Map<Integer, Role> idMap = new HashMap<>();

    static {
        for (Role role: Role.values()) {
            idMap.put(role.id, role);
        }
    }

    Role(int id, String description, Set<Privilege> privileges) {
        this.id = id;
        this.description = description;
        this.privileges = privileges;
    }

    public int getId() {
        return id;
    }

    public static Role formId(int id) {
        return idMap.get(id);
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }
}

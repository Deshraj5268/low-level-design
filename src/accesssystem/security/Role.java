package accesssystem.security;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private final String name;
    private final Set<String> allowedZones;

    // 🔐 Only allows general areas
    public static final Role DEFAULT_ROLE = new Role("EMPLOYEE", new HashSet<String>() {{
        add("ZONE_GENERAL");
    }});

    public Role(String name, Set<String> allowedZones) {
        this.name = name;
        this.allowedZones = allowedZones;
    }

    public boolean canAccess(String zoneId) {
        return allowedZones.contains(zoneId);
    }

    public String getName() { return name; }
    public Set<String> getAllowedZones() { return allowedZones; }
}

package accesssystem.core;

public class Zone {
    private final String zoneId;
    private final String name;
    private final boolean restricted;

    public Zone(String zoneId, String name, boolean restricted) {
        this.zoneId = zoneId;
        this.name = name;
        this.restricted = restricted;
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getName() {
        return name;
    }

    public boolean isRestricted() {
        return restricted;
    }

    @Override
    public String toString() {
        return name + (restricted ? " (Restricted)" : "");
    }
}

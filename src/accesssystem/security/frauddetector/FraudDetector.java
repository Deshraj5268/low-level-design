package accesssystem.security.frauddetector;

import accesssystem.core.AccessLog;
import accesssystem.core.AccessType;
import accesssystem.security.Role;

import java.util.*;

public class FraudDetector {
    private final Map<String, List<AccessLog>> logMap = new HashMap<>();

    public boolean isTailgating(String cardId, AccessType type) {
        List<AccessLog> logs = logMap.getOrDefault(cardId, new ArrayList<>());
        return !logs.isEmpty() && logs.get(logs.size() - 1).getType() == type;
    }

    public boolean isRestrictedAccess(Role role, String zoneId) {
        return !role.canAccess(zoneId);
    }

    public void log(String cardId, AccessType type) {
        logMap.computeIfAbsent(cardId, k -> new ArrayList<>())
                .add(new AccessLog(cardId, type));
    }

    public List<AccessLog> getLogs(String cardId) {
        return logMap.getOrDefault(cardId, Collections.emptyList());
    }
}

package accesssystem.security;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {
    private final Map<String, LocalDateTime> lastAccess = new HashMap<>();
    private static final int SECONDS_LIMIT = 5;

    public boolean isAllowed(String cardId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime last = lastAccess.getOrDefault(cardId, now.minusSeconds(SECONDS_LIMIT + 1));
        boolean allowed = now.isAfter(last.plusSeconds(SECONDS_LIMIT));
        System.out.println("last punch "+now);
        if (allowed) {
            lastAccess.put(cardId, now);
        }
        return allowed;
    }
}

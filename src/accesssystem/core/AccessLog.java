package accesssystem.core;

import java.time.LocalDateTime;

public class AccessLog {
    private final String cardId;
    private final AccessType type;
    private final LocalDateTime timestamp;

    public AccessLog(String cardId, AccessType type) {
        this.cardId = cardId;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public String getCardId() { return cardId; }
    public AccessType getType() { return type; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

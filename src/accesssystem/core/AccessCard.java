package accesssystem.core;

public class AccessCard {
    private final String cardId;
    private final String employeeId;
    private boolean active;

    public AccessCard(String cardId, String employeeId) {
        this.cardId = cardId;
        this.employeeId = employeeId;
        this.active = true;
    }

    public String getCardId() { return cardId; }
    public String getEmployeeId() { return employeeId; }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }
}

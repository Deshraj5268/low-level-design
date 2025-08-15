package accesssystem.simulation;

import accesssystem.core.AccessCard;
import accesssystem.core.AccessType;
import accesssystem.service.AttendanceManager;

public class RestrictedAreaScenario implements AccessScenario {
    private final AttendanceManager attendanceManager;
    private final AccessCard card;
    private final String restrictedZoneId;

    public RestrictedAreaScenario(AttendanceManager attendanceManager, AccessCard card, String restrictedZoneId) {
        this.attendanceManager = attendanceManager;
        this.card = card;
        this.restrictedZoneId = restrictedZoneId;
    }

    @Override
    public void run() {
        System.out.println(" Simulating restricted zone violation...");
        try {
            attendanceManager.punch(card.getCardId(), AccessType.ENTRY, restrictedZoneId);
        } catch (RuntimeException e) {
            System.out.println("message " + e.getMessage());
        }
    }

    @Override
    public String name() {
        return "RestrictedArea Scenario";
    }
}

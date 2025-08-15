package accesssystem.simulation;

import accesssystem.core.AccessCard;
import accesssystem.core.AccessType;
import accesssystem.service.AttendanceManager;

public class SimulateFraudScenario implements AccessScenario {
    private final AttendanceManager attendanceManager;
    private final AccessCard card;

    public SimulateFraudScenario(AttendanceManager attendanceManager, AccessCard card) {
        this.attendanceManager = attendanceManager;
        this.card = card;
    }

    @Override
    public void run() {
        System.out.println(" Simulating tailgating (duplicate IN)...");
        attendanceManager.punch(card.getCardId(), AccessType.IN);
    }

    @Override
    public String name() {
        return "Simulate Fraud Scenario";
    }
}
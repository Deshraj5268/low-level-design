package accesssystem.simulation;

import accesssystem.core.AccessCard;
import accesssystem.core.AccessType;
import accesssystem.service.AttendanceManager;

public class PunchInOutScenario implements AccessScenario {
    private final AttendanceManager attendanceManager;
    private final AccessCard card;

    public PunchInOutScenario(AttendanceManager attendanceManager, AccessCard card) {
        this.attendanceManager = attendanceManager;
        this.card = card;
    }

    @Override
    public void run() {
        System.out.println(" Performing IN and OUT punches for cardId :"+card.getCardId());
        attendanceManager.punch(card.getCardId(), AccessType.IN);
        attendanceManager.punch(card.getCardId(), AccessType.OUT);
    }

    @Override
    public String name() {
        return "Punch IN/OUT Scenario";
    }
}
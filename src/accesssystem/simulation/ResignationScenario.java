package accesssystem.simulation;

import accesssystem.core.*;
import accesssystem.service.*;

public class ResignationScenario implements AccessScenario {
    private final OrganizationManager orgManager;
    private final CardFactory cardFactory;
    private final AttendanceManager attendanceManager;
    private final Employee emp;
    private final AccessCard card;

    public ResignationScenario(OrganizationManager orgManager, CardFactory cardFactory, AttendanceManager attendanceManager, Employee emp, AccessCard card) {
        this.orgManager = orgManager;
        this.cardFactory = cardFactory;
        this.attendanceManager = attendanceManager;
        this.emp = emp;
        this.card = card;
    }

    @Override
    public void run() {
        System.out.println(" Resigning employee and testing access...");
        boolean isResigned = orgManager.resignEmployee(emp.getId());
        cardFactory.deactivateCardsByEmployee(emp.getId());
        System.out.println("Employee :"+emp.getName()+" is resigned successfully ?"+isResigned + " with cardId :"+card.getCardId());

        try {
            System.out.println("resigned employee :"+emp.getName()+" try to punchIn ");
            attendanceManager.punch(card.getCardId(), AccessType.IN);
        } catch (Exception e) {
            System.out.println("message " + e.getMessage());
        }
    }

    @Override
    public String name() {
        return "Resignation Scenario";
    }
}

package accesssystem.service;

import accesssystem.core.AccessCard;
import accesssystem.core.AccessType;
import accesssystem.core.Employee;
import accesssystem.security.RateLimiter;
import accesssystem.security.frauddetector.FraudDetector;

import java.util.Map;

public class AttendanceManager {
    private final Map<String, Employee> employeeMap;
    private final Map<String, AccessCard> cardMap;
    private final RateLimiter rateLimiter;
    private final FraudDetector fraudDetector;

    public AttendanceManager(Map<String, Employee> employeeMap,
                             Map<String, AccessCard> cardMap,
                             RateLimiter rateLimiter,
                             FraudDetector fraudDetector) {
        this.employeeMap = employeeMap;
        this.cardMap = cardMap;
        this.rateLimiter = rateLimiter;
        this.fraudDetector = fraudDetector;
    }

    public void punch(String cardId, AccessType type) {
        Employee emp = getIfEmployeeExist(cardId);
        //if (!rateLimiter.isAllowed(cardId)) throw new RuntimeException("Too many accesses. Try later.");
        if (fraudDetector.isTailgating(cardId, type)){
            System.out.println("tailgating is detected for : " + "name :"+ emp.getName() +" and cardId is :"+cardId+ " - " + type);
            throw new RuntimeException("Suspicious duplicate entry.");
        }
        fraudDetector.log(cardId, type);
        System.out.println("Access granted: " + "for employee :"+emp.getName() +" - " + type);
    }

    public void punch(String cardId, AccessType type, String zoneId) {
        Employee emp = getIfEmployeeExist(cardId);

        // Fraud alert for unauthorized zone
        if (fraudDetector.isRestrictedAccess(emp.getRole(), zoneId)) {
            System.out.println("Unauthorized access is detected for : " + "name :"+ emp.getName() +" and cardId is :"+cardId+ " - " + type + " - " + zoneId);
            throw new RuntimeException("Fraud Alert: Unauthorized zone access attempt detected!");
        }

        if (fraudDetector.isTailgating(cardId, type)) {
            System.out.println("tailgating is detected for : " + "name :"+ emp.getName() +" and cardId is :"+cardId+ " - " + type + " - " + zoneId);
            throw new RuntimeException("Fraud Alert: Suspicious duplicate entry detected (tailgating).");
        }

        fraudDetector.log(cardId, type);
        System.out.println("Access granted: " + "for employee :"+ emp.getName() + " - " + type + " - " + zoneId);
    }

    private Employee getIfEmployeeExist(String cardId) {
        AccessCard card = cardMap.get(cardId);
        if (card == null || !card.isActive()) throw new RuntimeException("Invalid or deactivated card for card "+cardId);
        Employee emp = employeeMap.get(card.getEmployeeId());
        if (emp == null || !emp.isActive()) throw new RuntimeException("Employee is not active.");
        return emp;
    }

}

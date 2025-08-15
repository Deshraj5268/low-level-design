package accesssystem;

import accesssystem.core.AccessCard;
import accesssystem.core.Employee;
import accesssystem.security.RateLimiter;
import accesssystem.security.frauddetector.FraudDetector;
import accesssystem.service.AttendanceManager;
import accesssystem.service.CardFactory;
import accesssystem.service.OrganizationManager;
import accesssystem.simulation.*;
import accesssystem.util.DataGenerationUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
* asked in flipkart
* */
public class OfficeAccessSystem {
    public static void main(String[] args) {
        Map<String, Employee> employeeMap = new HashMap<>();
        Map<String, AccessCard> cardMap = new HashMap<>();

        OrganizationManager orgManager = new OrganizationManager(employeeMap);
        CardFactory cardFactory = new CardFactory(cardMap);
        AttendanceManager attendanceManager = new AttendanceManager(employeeMap, cardMap, new RateLimiter(), new FraudDetector());

        RegisterEmployeeScenario registerScenario = new RegisterEmployeeScenario(orgManager, cardFactory);
        registerScenario.run();

        List<Employee> employees = registerScenario.getEmployees();
        List<AccessCard> cards = registerScenario.getCards();


        for(int i=0;i< employees.size();i++) {
            System.out.println("case :"+i);
            AccessScenario punchInOutScenario = new PunchInOutScenario(attendanceManager, cards.get(i));
            AccessScenario simulateFraudScenario = new SimulateFraudScenario(attendanceManager, cards.get(i));
            RestrictedAreaScenario restrictedAreaScenario = new RestrictedAreaScenario(attendanceManager, cards.get(i),
                    DataGenerationUtil.allZone.get(i));
            AccessScenario resignationScenario = new ResignationScenario(orgManager, cardFactory, attendanceManager, employees.get(i), cards.get(i));

            List<AccessScenario> scenarios = Arrays.asList(
                    punchInOutScenario, simulateFraudScenario, restrictedAreaScenario
                    , resignationScenario
            );


            new SimulationRunner(scenarios).runAll();
        }

    }
}

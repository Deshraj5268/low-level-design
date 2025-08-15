package accesssystem.simulation;

import accesssystem.core.AccessCard;
import accesssystem.core.Employee;
import accesssystem.service.CardFactory;
import accesssystem.service.OrganizationManager;
import accesssystem.util.DataGenerationUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterEmployeeScenario implements AccessScenario {
    private final OrganizationManager orgManager;
    private final CardFactory cardFactory;
    private List<Employee> employees;
    private List<AccessCard> cards;

    public RegisterEmployeeScenario(OrganizationManager orgManager, CardFactory cardFactory) {
        this.orgManager = orgManager;
        this.cardFactory = cardFactory;
        this.employees = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    @Override
    public void run() {
        System.out.println("Registering employee and card...");
        List<Employee> employees = DataGenerationUtil.createEmployees();
        List<String> cards = Arrays.asList("CARD001", "CARD002", "CARD003", "CARD004");
        // createCardInBulk
        for(int i=0;i<employees.size();i++) {
            Employee employee = orgManager.registerEmployee(employees.get(i));
            AccessCard card = cardFactory.createCard(cards.get(i), employee.getId());
            this.employees.add(employee);
            this.cards.add(card);
            System.out.println("employee is registered and name is :" + employee.getName() + " and card is :" +
                    card.getCardId());
        }
    }

    @Override
    public String name() {
        return "Register Employee Scenario";
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<AccessCard> getCards() {
        return cards;
    }
}

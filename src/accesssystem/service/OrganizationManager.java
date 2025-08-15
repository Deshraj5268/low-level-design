package accesssystem.service;

import accesssystem.core.Employee;
import accesssystem.security.Role;

import java.util.List;
import java.util.Map;

public class OrganizationManager {
    private final Map<String, Employee> employeeMap;

    public OrganizationManager(Map<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    public Employee registerEmployee(String id, String name, String orgId) {
        Employee emp = new Employee(id, name, orgId);
        employeeMap.put(id, emp);
        return emp;
    }

    public Employee registerEmployee(String id, String name, String orgId, Role role) {
        Employee emp = new Employee(id, name, orgId, role);
        employeeMap.put(id, emp);
        return emp;
    }

    public Employee registerEmployee(Employee employee) {
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public void registerEmployee(List<Employee> employees) {
        for(Employee employee: employees) {
            employeeMap.put(employee.getId(), employee);
        }
    }

    public boolean resignEmployee(String employeeId) {
        if (employeeMap.containsKey(employeeId)) {
            employeeMap.get(employeeId).deactivate();
            return true;
        }
        return false;
    }

    public boolean isActive(String employeeId) {
        return employeeMap.containsKey(employeeId) && employeeMap.get(employeeId).isActive();
    }
}

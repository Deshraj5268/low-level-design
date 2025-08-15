package accesssystem.core;

import accesssystem.security.Role;

public class Employee {
    private final String id;
    private final String name;
    private final String organizationId;
    private boolean active;

    private Role role;

    public Role getRole() { return role; }

    public Employee(String id, String name, String organizationId) {
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
        this.role = Role.DEFAULT_ROLE;
        this.active = true;
    }

    public Employee(String id, String name, String organizationId, Role role) {
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
        this.active = true;
        this.role = (role != null) ? role : Role.DEFAULT_ROLE;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getOrganizationId() { return organizationId; }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}

package accesssystem.util;

import accesssystem.core.Employee;
import accesssystem.core.Zone;
import accesssystem.security.Role;
import accesssystem.security.RoleType;

import java.util.*;

public class DataGenerationUtil {

    public static Map<RoleType, Role> roles = new HashMap<>();
    public static List<String > allZone;

    public static List<Employee> employeesList;
    private DataGenerationUtil(){

    }

    static {

        Zone general = new Zone("ZONE_GENERAL", "General Area", false);
        Zone cafeteria = new Zone("CAFETERIA", "Cafeteria", false);
        Zone serverRoom = new Zone("ZONE_SERVER", "Server Room", true);
        Zone vault = new Zone("ZONE_VAULT", "Finance Vault", true);
        allZone = Arrays.asList(general.getZoneId(), cafeteria.getZoneId(), serverRoom.getZoneId(), vault.getZoneId());

        Set<String> generalZone = new HashSet<>();
        generalZone.add(allZone.get(0));
        generalZone.add(allZone.get(1));
        Role generalRole = new Role(RoleType.GENERAL.name(), generalZone);

        Set<String> adminZones = new HashSet<>();
        adminZones.add(allZone.get(0));
        adminZones.add(allZone.get(2));
        adminZones.add(allZone.get(3));
        Role adminRole = new Role(RoleType.ADMIN.name(), adminZones);

        Set<String> itZones = new HashSet<>();
        adminZones.add(allZone.get(0));
        adminZones.add(allZone.get(2));;
        Role itRole = new Role(RoleType.IT_SUPPORT.name(), itZones);

        roles.put(RoleType.GENERAL, generalRole);
        roles.put(RoleType.ADMIN, adminRole);
        roles.put(RoleType.IT_SUPPORT,itRole);
    }
    public static List<Employee> createEmployees() {
        Map<RoleType, Role> roleAndZoneMapping = getRoleAndZoneMapping();
        Employee raj = new Employee("E100", "raj","ORG1", roleAndZoneMapping.get(RoleType.GENERAL)); // only general access
        Employee harish = new Employee("E101", "harish","ORG1", roleAndZoneMapping.get(RoleType.ADMIN)); // full access
        Employee ranjeet = new Employee("E102", "ranjeet","ORG1", roleAndZoneMapping.get(RoleType.IT_SUPPORT)); // general + server room

        Employee deshraj = new Employee("E103", "deshraj","ORG1", roleAndZoneMapping.get(RoleType.IT_SUPPORT)); // general + server room

        employeesList = Arrays.asList(raj,harish,ranjeet, deshraj);
        return employeesList;
    }
    public static List<String> getListOfZone(){
        return allZone;
    }

    public static Map<RoleType, Role> getRoleAndZoneMapping(){
        return roles;
    }
}

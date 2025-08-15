package accesssystem.policy;

import java.time.DayOfWeek;

public interface AccessPolicy {
    boolean isWorkingDay(DayOfWeek day);
}

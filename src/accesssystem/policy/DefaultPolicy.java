package accesssystem.policy;

import java.time.DayOfWeek;

public class DefaultPolicy implements AccessPolicy {
    @Override
    public boolean isWorkingDay(DayOfWeek day) {
        return day != DayOfWeek.SUNDAY;
    }
}

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ExitPlan {
    private boolean isEndTimeSelected;
    private LocalTime endTime;
    private boolean isHooksSelected;
    private int hooks;
    private boolean isCastsSelected;
    private int casts;
    private boolean isHoursSelected;
    private boolean isNeverSelected;
    private boolean isBuffsUsedUpSelected;
    private boolean isLogoutSelected;
    private boolean isShutDownPcSelected;

    public ExitPlan(boolean isEndTimeSelected, LocalTime endTime, boolean isHooksSelected, int hooks,
                    boolean isCastsSelected, int casts, boolean isHoursSelected, int hours, boolean isNeverSelected,
                    boolean isBuffsUsedUpSelected, boolean isLogoutSelected, boolean isShutDownPcSelected) {
        this.isEndTimeSelected = isEndTimeSelected;
        this.endTime = endTime;
        this.isHooksSelected = isHooksSelected;
        this.hooks = hooks;
        this.isCastsSelected = isCastsSelected;
        this.casts = casts;
        this.isHoursSelected = isHoursSelected;
        this.isNeverSelected = isNeverSelected;
        this.isBuffsUsedUpSelected = isBuffsUsedUpSelected;
        this.isLogoutSelected = isLogoutSelected;
        this.isShutDownPcSelected = isShutDownPcSelected;

        if (isHoursSelected)
            this.endTime = LocalDateTime.now().toLocalTime().plusHours(hours);
    }

    public String getCommandArguments() {
        return isEndTimeSelected + " " + endTime.getHour() + ":" + endTime.getMinute() + ":" + endTime.getSecond() + " " +
                isCastsSelected + " " + casts + " " +
                isHooksSelected + " " + hooks + " " +
                isHoursSelected + " " +
                isNeverSelected + " " +
                isBuffsUsedUpSelected + " " +
                isLogoutSelected + " " +
                isShutDownPcSelected;
    }
}

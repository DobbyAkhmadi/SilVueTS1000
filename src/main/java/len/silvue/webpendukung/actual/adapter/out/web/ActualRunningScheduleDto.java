package len.silvue.webpendukung.actual.adapter.out.web;
import lombok.*;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class ActualRunningScheduleDto {
    private int idActualPlan;
    private Date actualCode;
    private Date timeData;
    private String trainActualPlan;
    private String ruteRoleActualPlan;
    private String statiunActualPlan;
    private int platformActualPlan;
    private int platformSchedulePlan;
    private Date arriveActualPlan;
    private Date departActualPlan;
    private Date arriveSchedule;
    private Date departSchedule;
    private String typePlan;
    private String statusActualPlan;
    private String delayActualPlan;
    private String comments;
    private String departmentActual;
    private String vehicleTrainActualPlan;
    private boolean flagActualPlan;
    private int indexActual;
    private DepartementDto departement;
    private ProblemDto problem;
}

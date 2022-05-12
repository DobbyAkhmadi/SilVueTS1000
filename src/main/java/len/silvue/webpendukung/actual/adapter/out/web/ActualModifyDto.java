package len.silvue.webpendukung.actual.adapter.out.web;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActualModifyDto {
    private int idActualPlan;
    private String actualCode;
    private Date timeData;
    private String trainActualPlan;
    private String ruteRoleActualPlan;
    private String statiunActualPlan;
    private int platformActualPlan;
    private int platformSchedulePlan;
    private String arriveActualPlan;
    private String departActualPlan;
    private String arriveSchedule;
    private String departSchedule;
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

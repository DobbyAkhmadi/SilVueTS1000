package len.silvue.webpendukung.actual.adapter.in.web;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ModifyActualCommand {
    private int idActualPlan;
    private String timeData;
    private String actualCode;
    private String trainActualPlan;
    private String tempActualPlan;
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
    private int IdDepartement;
    private int IdProblem;
}

package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACTUALPLAN")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ActualPlan {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDACTUALPLAN")
    private Integer idActualPlan;
    @ManyToOne
    @JoinColumn(name = "IDLOGMESSAGE")
    private LogMessage logMessage;
    @Temporal(TemporalType.DATE)
    @Column(name = "TIMEDATA")
    private Date timeData;
    @Column(name = "TRAINACTUALPLAN")
    private String trainActualPlan;
    @Column(name = "RUTEROLEACTUALPLAN")
    private String ruteRoleActualPlan;
    @Column(name = "STATIUNACTUALPLAN")
    private String statiunActualPlan;
    @Column(name = "PLATFORMACTUALPLAN")
    private Integer platformActualPlan;
    @Column(name = "PLATFORMSCHEDULEPLAN")
    private Integer platformSchedulePlan;
    @Column(name = "ARRIVEACTUALPLAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arriveActualPlan;
    @Column(name = "ACTUALCODE")
    @Temporal(TemporalType.DATE)
    private Date actualCode;
    @Column(name = "DEPARTACTUALPLAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departActualPlan;
    @Column(name = "ARRIVESCHEDULE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arriveSchedule;
    @Column(name = "DEPARTSCHEDULE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departSchedule;
    @Column(name = "TYPEPLAN")
    private String typePlan;
    @Column(name = "STATUSACTUALPLAN")
    private String statusActualPlan;
    @Column(name = "DELAYACTUALPLAN")
    private String delayActualPlan;
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "DEPARTMENTACTUAL")
    private String departmentActual;
    @Column(name = "VEHICLETRAINACTUALPLAN")
    private String vehicleTrainActualPlan;
    @Column(name = "FLAGACTUALPLAN")
    private Boolean flagActualPlan;
    @Column(name = "INDEXACTUAL")
    private Integer indexActual;
    @ManyToOne
    @JoinColumn(name = "IDDEPARTEMENT")
    private Departement departement;
    @ManyToOne
    @JoinColumn(name = "IDPROBLEM")
    private Problem problem;
}

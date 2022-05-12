package len.silvue.webpendukung.domains;

import len.silvue.webpendukung.domains.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TODAYRUNNINGSCHEDULE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class TrainLocationRunning {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDTODAYRUNNINGSCHEDULE")
    private Integer idTodayRunningSchedule;
    @ManyToOne
    @JoinColumn(name = "IDTRAIN")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "IDNUMBERTRAIN")
    private NumberTrain numberTrain;
    @ManyToOne
    @JoinColumn(name = "IDPERONFROMTODAY")
    private Peron peronFrom;
    @ManyToOne
    @JoinColumn(name = "IDRUTEROLE")
    private RuteRole ruteRole;
    @ManyToOne
    @JoinColumn(name = "IDPERONTOTODAY")
    private Peron peronTo;
    @ManyToOne
    @JoinColumn(name = "IDTYPEMASTERPLAN")
    private TypeMasterPlan typeMasterPlan;
    @Column(name = "DEPART")
    @Temporal(TemporalType.TIME)
    private Date depart;
    @Column(name = "ARRIVAL")
    @Temporal(TemporalType.TIME)
    private Date arrival;
    @Column(name = "DWELLINGTIME")
    private Long dwellingTime;
    @Column(name = "FLAGTODAY")
    private Integer flagMasterPlan;
    @Column(name = "CONFLICTFLAGTODAY")
    private Integer flagCheckConflict;
    @Column(name = "ACTUALCODE")
    @Temporal(TemporalType.TIME)
    private Date actualCode;
}

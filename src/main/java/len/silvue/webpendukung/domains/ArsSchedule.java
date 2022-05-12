package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ARSSCHEDULE")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArsSchedule {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDARSSCHEDULE")
    private Integer idArsSchedule;
    @ManyToOne
    @JoinColumn(name = "IDTYPEMASTERPLAN")
    private TypeMasterPlan typeMasterPlan;
    @ManyToOne
    @JoinColumn(name = "IDNUMBERTRAIN")
    private NumberTrain numberTrain;
    @ManyToOne
    @JoinColumn(name = "IDPERONFROMARS")
    private Peron peronFromArs;
    @ManyToOne
    @JoinColumn(name = "IDPERONTOARS")
    private Peron peronToArs;
    @ManyToOne
    @JoinColumn(name = "IDTRAIN")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "IDRUTEROLE")
    private RuteRole ruteRole;
    @Temporal(TemporalType.TIME)
    @Column(name = "DEPARTARS")
    private Date departArs;
    @Column(name = "ARRIVALARS")
    @Temporal(TemporalType.TIME)
    private Date arrivalArs;
    @Column(name = "SCHEDULESTATUSARS")
    private int scheduleStatusArs;
    @Column(name = "ROUTESETTINGSTATUS")
    private String routeSettingStatus;
    @Column(name = "FLAGARSSCHEDULE")
    private int flagArsSchedule;
    @Temporal(TemporalType.DATE)
    @Column(name = "ACTUALCODEARS")
    private Date actualCodeArs;
}

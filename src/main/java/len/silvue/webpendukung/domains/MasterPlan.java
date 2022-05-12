package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "masterplan")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class MasterPlan {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDMASTERPLAN")
    private Integer IdMasterPlan;
    @ManyToOne
    @JoinColumn(name = "IDTRAIN")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "IDNUMBERTRAIN")
    private NumberTrain numberTrain;
    @ManyToOne
    @JoinColumn(name = "IDPERONFROM")
    private Peron peronFrom;
    @ManyToOne
    @JoinColumn(name = "IDRUTEROLE")
    private RuteRole ruteRole;
    @ManyToOne
    @JoinColumn(name = "IDPERONTO")
    private Peron peronTo;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IDTYPEMASTERPLAN")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TypeMasterPlan typeMasterPlan;
    @Column(name = "DEPART")
    @Temporal(TemporalType.TIME)
    private Date depart;
    @Column(name = "ARRIVAL")
    @Temporal(TemporalType.TIME)
    private Date arrival;
    @Column(name = "DWELLINGTIME")
    private long dwellingTime;
    @Column(name = "FLAGMASTERPLAN")
    private Integer flagMasterPlan;
    @Column(name = "FLAGCHECKCONFLICT")
    private Integer flagCheckConflict;
}

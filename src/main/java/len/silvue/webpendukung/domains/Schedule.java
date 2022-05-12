package len.silvue.webpendukung.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SCHEDULE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @Column(name = "IDSCHEDULE")
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer scheduleId;
    @Column(name = "SCHEDULENAME")
    private String scheduleName;
    @ManyToOne
    @JoinColumn(name = "IDTYPEMASTERPLAN")
    private TypeMasterPlan typeMasterPlan;
    @ManyToOne
    @JoinColumn(name = "IDTRAIN")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "IDRUTEROLE")
    private RuteRole ruteRole;
    @Column(name = "FLAGMASTER")
    private String flagMaster;
    @Column(name = "NEXTDAY")
    private String nextDay;
}

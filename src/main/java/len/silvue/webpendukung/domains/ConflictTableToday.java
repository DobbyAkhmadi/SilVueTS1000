package len.silvue.webpendukung.domains;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "CONFLICTTABLETODAY")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ConflictTableToday {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDCONFLICTTABLEMASTER")
    private Integer idConflictTableMaster;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "IDTODAYRUNNINGSCHEDULEA",referencedColumnName="IDTODAYRUNNINGSCHEDULE")
    private TodayRunningSchedule TodayA;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "IDTODAYRUNNINGSCHEDULEB",referencedColumnName="IDTODAYRUNNINGSCHEDULE")
    private TodayRunningSchedule TodayB;
}

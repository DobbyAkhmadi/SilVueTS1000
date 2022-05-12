package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TRAININFORMATION")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TrainInformation {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDTRAININFORMATION")
    private Integer idTrainInformation;
    @ManyToOne
    @JoinColumn(name = "IDTODAYRUNNINGSCHEDULE")
    private TodayRunningSchedule todayRunningSchedule;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICK")
    private RouteStick routeStick;
}

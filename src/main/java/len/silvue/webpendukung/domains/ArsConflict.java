package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ARSCONFLICT")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArsConflict {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDARSCONFLICT")
    private Integer idArsConflict;
    @ManyToOne
    @JoinColumn(name = "IDARSSCHEDULENOW")
    private ArsSchedule arsScheduleNow;
    @ManyToOne
    @JoinColumn(name = "IDARSSCHEDULENEXT")
    private ArsSchedule arsScheduleNext;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICK")
    private RouteStick routeStick;


}

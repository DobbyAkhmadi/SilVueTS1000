package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ARRIVETRACK")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ArriveTrack {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDARRIVETRACK")
    private Integer idArriveTrack;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICK")
    private RouteStick routeStick;
}

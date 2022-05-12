package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ROUTESTICKINFORMATION")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RouteStickInformation {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDROUTESTICKINFORMATION")
    private Integer idRouteStickInformation;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICK")
    private RouteStick routeStick;
    @Column(name = "INTERMEDIATEBLOCK")
    private Integer intermediateBlock;
}

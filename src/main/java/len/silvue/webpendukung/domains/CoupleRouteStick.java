package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "COUPLEROUTESTICK")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CoupleRouteStick {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDCOUPLEROUTESTICK")
    private Integer idCoupleRouteStick;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICKA")
    private RouteStick routeStickA;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICKB")
    private RouteStick routeStickB;
}

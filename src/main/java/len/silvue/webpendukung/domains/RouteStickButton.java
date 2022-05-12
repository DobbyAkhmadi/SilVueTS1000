package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "routestickbutton")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RouteStickButton {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDROUTESTICKBUTTON")
    private Integer idRouteStickButton;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IDROUTEBUTTON")
    private RouteButton routeButton;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICK")
    private RouteStick routeStick;
    @Column(name = "NOROUTESTICKBUTTON")
    private Integer noRouteStickButton;
    @Column(name = "MAXROUTESTICKBUTTON")
    private Integer maxRouteStickButton;
}

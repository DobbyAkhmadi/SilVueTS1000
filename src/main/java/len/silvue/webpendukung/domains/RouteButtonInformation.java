package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ROUTEBUTTONINFORMATION")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RouteButtonInformation {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDROUTEBUTTONINFORMATION")
    private Integer idRouteButtonInformation;
    @ManyToOne
    @JoinColumn(name = "IDPERON")
    private Peron peron;
    @ManyToOne
    @JoinColumn(name = "IDPERONTO")
    private Peron peronTo;
    @ManyToOne
    @JoinColumn(name = "IDROUTEBUTTON")
    private RouteButton routeButton;
    @ManyToOne
    @JoinColumn(name = "IDROUTEBUTTONTO")
    private RouteButton routeButtonTo;
    @ManyToOne
    @JoinColumn(name = "IDSTATION")
    private Station station;
}

package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ROUTEBUTTON")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RouteButton {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDROUTEBUTTON")
    private Integer idRouteButton;
    @ManyToOne
    @JoinColumn(name = "IDSIGNALDETAIL")
    private SignalDetail signalDetail;
    @Column(name = "NAMESIGNAL")
    private String nameSignal;
    @Column(name = "NAMESIGNALTO")
    private String nameSignalTo;
}

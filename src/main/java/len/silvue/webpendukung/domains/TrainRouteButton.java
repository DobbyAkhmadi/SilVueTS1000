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
public class TrainRouteButton {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDTRAINROUTEBUTTON")
    private Integer idTrainRouteButton;
    @ManyToOne
    @JoinColumn(name = "IDTRAIN")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "IDROUTEBUTTON")
    private RouteButton routeButton;
}

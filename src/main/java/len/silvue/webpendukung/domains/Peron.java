package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "peron")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Peron {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDPERON")
    private Integer idPeron;
    @ManyToOne
    @JoinColumn(name = "IDSTATION")
    private Station station;
    @ManyToOne
    @JoinColumn(name ="IDROUTESTICK")
    private RouteStick routeStick;
    @Column(name = "NOPERON")
    private Integer noPeron;
    @Column(name = "SIGNALFLAG")
    private Integer signalFlag;
}

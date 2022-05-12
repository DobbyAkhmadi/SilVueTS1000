package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "weselroutestickdetail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WeselRouteStickDetail {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDWESELROUTESTICKDETAIL")
    private Integer idWeselRouteStickDetail;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICK")
    private RouteStick routeStick;

    @ManyToOne
    @JoinColumn(name = "IDWESEL")
    private Wesel wesel;

}

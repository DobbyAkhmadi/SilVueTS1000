package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "routestickweselpoint")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RouteStickWeselPoint {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDROUTESTICKWESELPOINT")
    private Integer idRouteStickWeselPoint;

    @ManyToOne
    @JoinColumn(name = "IDROUTESTICK")
    private RouteStick routeStick;

    @ManyToOne
    @JoinColumn(name = "IDROUTESTICKBEFORE")
    private RouteStick routeStickBefore;

    @ManyToOne
    @JoinColumn(name = "IDROUTESTICKAFTER")
    private RouteStick routeStickAfter;

    @ManyToOne
    @JoinColumn(name = "IDWESELPOINT")
    private WeselPoint weselPoint;

    @Column(name = "NOWESELIMPORT")
    private Integer noWeselImport;

    @Column(name = "MAXNUMBERWESELPOINT")
    private Integer maxNumberWeselPoint;
}

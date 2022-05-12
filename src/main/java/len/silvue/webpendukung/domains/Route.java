package len.silvue.webpendukung.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ROUTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Route {
    @Id
    @Column(name = "route_id")
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer routeId;
    @ManyToOne
    @JoinColumn(name = "IDSTATION")
    private Station station;
    @ManyToOne
    @JoinColumn(name = "IDRUTEROLE")
    private RuteRole ruteRole;
    @ManyToOne
    @JoinColumn(name = "IDPERON")
    private Peron peron;
    @Column(name = "arrival")
    @Temporal(TemporalType.TIME)
    private Date arrival;
    @Column(name = "depart")
    @Temporal(TemporalType.TIME)
    private Date depart;
    @Column(name = "NEXTDAY")
    private String nextDay;
}

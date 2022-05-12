package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "routesticktrain")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RouteStickTrain {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDROUTESTICKTRAIN")
    private Integer idRouteStickTrain;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICK")
    private RouteStick routeStick;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICKBEFORE")
    private RouteStick routeStickBefore;
    @ManyToOne
    @JoinColumn(name = "IDTRAIN")
    private Train train;
    @Temporal(value = TemporalType.DATE)
    @Column(name = "INPUTDATE")
    private Date inputDate;
    @Column(name = "STATUSTEDO")
    private String statusTedo;
    @Column(name = "STATUSREDO")
    private String statusRedo;
    @Column(name = "STATUSREQUEST")
    private String statusRequest;
}

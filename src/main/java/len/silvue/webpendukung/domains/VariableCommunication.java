package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "variablecommunication")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class VariableCommunication {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDVARIABLECOMMUNICATION")
    private Integer idVariableCommunication;
    @ManyToOne
    @JoinColumn(name = "IDROUTESTICK")
    private RouteStick routeStick;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IDCGDEVICE")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CGDevice cgDevice;
    @Column(name = "VAROPC")
    private String varOPC;
    @ManyToOne
    @JoinColumn(name = "IDVARIABLETRACKOPCREDO")
    private VariableTrackOPC updateTrackOPCRedo;
    @ManyToOne
    @JoinColumn(name = "IDVARIABLETRACKOPCTEDO")
    private VariableTrackOPC updateTrackOPCTedo;
}

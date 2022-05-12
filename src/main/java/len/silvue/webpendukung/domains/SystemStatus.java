package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "SYSTEMSTATUS")
public class SystemStatus {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDSYSTEMSTATUS")
    private Integer idSystemStatus;
    @Column(name = "CLIENTSTATUS")
    private Integer clientStatus;
    @Column(name = "CTCSTATUS")
    private Integer ctcStatus;
    @Column(name = "VARIABLENOTCONNECT")
    private Integer variableNotConnect;
    @Column(name = "TDSSTATUS")
    private Integer tdsStatus;
    @Column(name = "ARSSTATUS")
    private Integer arsStatus;
    @Column(name = "ATRSTATUS")
    private Integer atrStatus;
    @Column(name = "OPCSTATUS")
    private Integer opcStatus;
    @Column(name = "REDUNDANCYSTATUS")
    private Integer redundancyStatus;
    @Column(name = "SLAVESTATUS")
    private Integer slaveStatus;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTUPDATEARS")
    private Date lastUpdateArs;
}

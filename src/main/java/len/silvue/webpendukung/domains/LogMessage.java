package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOGMESSAGE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class LogMessage {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDLOGMESSAGE")
    private Integer idLogMessage;
    @ManyToOne
    @JoinColumn(name = "IDDEPARTMENT")
    private Departement departement;
    @Column(name = "MESSAGE")
    private String message;
    @Temporal(TemporalType.DATE)
    @Column(name = "TIMELOGMESSAGE")
    private Date timeLogMessage;
    @Column(name = "USERLOGIN")
    private String userLogin;
}

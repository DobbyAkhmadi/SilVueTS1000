package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENTLOG")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventLog {
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
    private Integer idEventLog;
    @Column(name = "TIMELOGMESSAGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeLogMessage;
    @Column(name = "EVENTMESSAGE")
    private String eventMessage;
    @Column(name = "USERLOGIN")
    private String userLogIn;
}

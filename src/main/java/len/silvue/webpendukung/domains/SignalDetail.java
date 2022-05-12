package len.silvue.webpendukung.domains;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIGNALDETAIL")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SignalDetail {
    @Id
    @Column(name = "IDSIGNALDETAIL")
    private Integer idSignalDetail;
    @Column(name = "EMERGENCY")
    private String emergency;
    @Column(name = "SHUNT")
    private String shunt;
    @Column(name = "GREEN")
    private String green;
    @Column(name = "RED")
    private String red;
    @Column(name = "YELLOW")
    private String yellow;
}

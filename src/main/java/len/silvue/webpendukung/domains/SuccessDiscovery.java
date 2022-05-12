package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SUCCESSDISCOVERY")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SuccessDiscovery {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDSUCCESSDISCOVERY")
    private Integer idSuccessDiscovery;
    @Column(name = "SUCCESSNOTIFDISCOVERY")
    private Integer successNotifDiscovery;
}

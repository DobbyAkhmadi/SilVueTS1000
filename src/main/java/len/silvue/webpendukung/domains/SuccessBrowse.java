package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SUCCESSBROWSE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SuccessBrowse {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDSUCCESSNOTIF")
    private Integer idSuccessNotification;
    @Column(name = "SUCCESSNOTIF")
    private Integer successNotification;
}

package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SUCCESSBROWSETODAY")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SuccessBrowseConflict {
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

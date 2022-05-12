package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "UPDATETRACKOPCTEDO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateTrackOPCTedo {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDUPDATETRACKOPCTEDO")
    private Integer idUpdateTrackOPCTedo;
    @Column(name = "IDROUTESTICKTRAIN")
    private Integer routeStickTrain;
}

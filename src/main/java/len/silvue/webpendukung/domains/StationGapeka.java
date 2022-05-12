package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "STATIONGAPEKA")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class StationGapeka {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDSTATIONGAPEKA")
    private Integer idStationGapeka;
    @ManyToOne
    @JoinColumn(name = "IDSTATION")
    private Station station;
}

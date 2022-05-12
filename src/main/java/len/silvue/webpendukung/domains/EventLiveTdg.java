package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "EVENTLIVETDG")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class EventLiveTdg {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDEVENTLIVETDG")
    private Integer idEventLiveTdg;
    @ManyToOne
    @JoinColumn(name = "IDTRAIN")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "IDSTATION")
    private Station station;
    @Column(name = "STATUSEVENTLIVETDG")
    private String statusEventLiveTdg;
}

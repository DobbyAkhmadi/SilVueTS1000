package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "LISTRUTEDETAIL")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ListRuteDetail {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name ="IDLISTRUTEDETAIL")
    private Integer idListRuteDetail;
    @ManyToOne
    @JoinColumn(name = "IDRUTEROLE")
    private RuteRole ruteRole;
    @ManyToOne
    @JoinColumn(name = "IDSTATION")
    private Station station;
    @Column(name = "INDEXLISTRUTEDETAIL")
    private int indexListRuteDetail;
    @Column(name = "LOCUNITRUTE")
    private String locUnitRute;
}

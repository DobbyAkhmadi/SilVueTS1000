package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "LISTLINEDETAIL")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ListLineDetail {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDLISTLINEDETAIL")
    private Integer idListLineDetail;
    @ManyToOne
    @JoinColumn(name = "IDSTATION")
    private Station station;
    @ManyToOne
    @JoinColumn(name = "IDLINE")
    private Line line;
    @Column(name = "INDEXLISTLINEDETAIL")
    private Integer indexListLineDetail;
    private String locUnitLine;
}

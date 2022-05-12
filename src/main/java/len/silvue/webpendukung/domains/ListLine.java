package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "LISTLINE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ListLine {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDLISTLINE")
    private Integer idListLine;
    @ManyToOne
    @JoinColumn(name = "IDSTATION")
    private Station station;
    @ManyToOne
    @JoinColumn(name = "IDLINE")
    private Line line;
    @Column(name = "LISTLINENUMBER")
    private int listLineNumber;
    @Column(name = "LOCUNIT")
    private String locUnit;
}

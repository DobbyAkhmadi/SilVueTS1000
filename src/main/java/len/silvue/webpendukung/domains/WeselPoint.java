package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "weselpoint")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WeselPoint {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDWESELPOINT")
    private Integer idWeselPoint;

    @ManyToOne
    @JoinColumn(name = "IDWESEL")
    private Wesel wesel;

    @Column(name = "VARIABLEWESELPOINT")
    private String variableWeselPoint;

}

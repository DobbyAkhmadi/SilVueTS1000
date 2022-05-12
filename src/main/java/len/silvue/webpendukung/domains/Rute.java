package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "RUTE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Rute {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDRUTE")
    private Integer idRute;
    @ManyToOne
    @JoinColumn(name = "IDRUTEROLE")
    private RuteRole ruteRole;
    @Column(name = "NAMESTATION")
    private String nameStation;
    @Column(name = "NO")
    private Integer no;
}

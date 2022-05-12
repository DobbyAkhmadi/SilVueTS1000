package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "LINE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Line {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDLINE")
    private Integer idLine;
    @Column(name = "NAMELINE")
    private String nameLine;
}

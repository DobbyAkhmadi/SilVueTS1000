package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "colortrain")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ColorTrain {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDCOLORTRAIN")
    private Integer idColorTrain;
    @ManyToOne
    @JoinColumn(name = "IDTRAIN")
    private Train train;
    @Column(name = "COLORTRAIN")
    private String colorTrain;
}

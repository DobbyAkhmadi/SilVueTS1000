package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "routestick")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RouteStick {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDROUTESTICK")
    private Integer idRouteStick;
    @Column(name = "TRACK")
    private String track;
}

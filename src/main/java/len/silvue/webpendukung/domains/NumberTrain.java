package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "numbertrain")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NumberTrain {
    @Id
//    @GeneratedValue(
//            strategy= GenerationType.AUTO,
//            generator="native"
//    )
//    @GenericGenerator(
//            name = "native",
//            strategy = "native"
//    )
    @Column(name = "IDNUMBERTRAIN")
    private Integer idNumberTrain;
    @Column(name = "NUMBERVEHICLETRAIN")
    private String numberVehicleTrain;
}

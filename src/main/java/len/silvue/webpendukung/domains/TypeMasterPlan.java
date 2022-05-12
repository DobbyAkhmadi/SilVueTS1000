package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
@Table(name = "typemasterplan")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class TypeMasterPlan {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "IDTYPEMASTERPLAN")
    private Integer idTypeMasterPlan;
    @Column(name = "NAMETYPEMASTERPLAN")
    private String nameTypeMasterPlan;
}

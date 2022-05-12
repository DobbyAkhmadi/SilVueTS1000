package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "BROWSEVARIABLE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BrowseVariable {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDVARIABLEBROWSENAME")
    private Integer idVariableBrowseName;
    @Column(name = "VARIABLEBROWSENAME")
    private String variableBrowseName;
}

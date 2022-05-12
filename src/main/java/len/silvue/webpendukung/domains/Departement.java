package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTEMENT")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Departement {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDDEPARTMENT")
    private Integer idDepartement;
    @Column(name = "NAMEDEPARTEMENT")
    private String nameDepartement;
    @Column(name = "CODEDEPARTMENT")
    private String codeDepartement;
    @Column(name = "DESCDEPARTMENT")
    private String descDepartement;
}

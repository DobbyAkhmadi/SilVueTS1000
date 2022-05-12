package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "PROBLEM")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Problem {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDPROBLEM")
    private Integer idProblem;
    @Column(name = "PROBLEM_NAME")
    private String problemName;
    @Column(name = "PROBLEM_CODE")
    private String problemCode;
    @Column(name = "PROBLEM_DESC")
    private String problemDesc;
    @ManyToOne
    @JoinColumn(name = "IDDEPARTEMENT")
    private Departement department;
}

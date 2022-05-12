package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "stationinformation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StationInformation {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDSTATIONINFORMATION")
    private Integer idStationInformation;
    @ManyToOne
    @JoinColumn(name = "IDSTATION")
    private Station station;
    @Column(name = "CODESTATION")
    private String codeStation;
    @Column(name = "KILOMETER")
    private Integer kilometer;
    @Column(name = "MDPL")
    private Integer mdpl;
    @Column(name = "AREA")
    private String area;

}

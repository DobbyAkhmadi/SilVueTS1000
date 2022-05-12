package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "MYSQLDUMP")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MysqlDump {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDMYSQLDUMP")
    private Integer idMysqlDump;
    @Column(name = "MYSQLDUMPNOTIF")
    private Integer mysqlDumpNotif;
}

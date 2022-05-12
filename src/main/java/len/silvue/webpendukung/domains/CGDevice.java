package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CGDevice {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDCGDEVICE")
    private Integer idCgDevice;
    @Column(name = "STATUSCGDEVICE")
    private String statusCgDevice;
    @Column(name = "IPCGDEVICE")
    private String ipCgDevice;
    @Column(name = "NAMECGDEVICE")
    private String nameCgDevice;
    @Column(name = "STATUSCGDEVICEREDO")
    private String statusCgDeviceRedo;
}

package len.silvue.webpendukung.tmconfig.adapter.out.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SystemStatusDto {
    private int idSystemStatus;
    private int clientStatus;
    private int ctcStatus;
    private int variableNotConnect;
    private int tdsStatus;
    private int arsStatus;
    private int atrStatus;
    private int opcStatus;
    private int redundancyStatus;
    private int slaveStatus;
    private Date lastUpdateArs;
}

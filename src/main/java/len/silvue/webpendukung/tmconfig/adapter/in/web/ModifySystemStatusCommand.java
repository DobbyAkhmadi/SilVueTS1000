package len.silvue.webpendukung.tmconfig.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifySystemStatusCommand {
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

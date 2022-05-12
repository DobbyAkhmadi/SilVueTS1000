package len.silvue.webpendukung.tmconfig.adapter.out.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ConfigurationDto {
    private Integer id;
    private String iptmis;
    private String redudancyStatus;
    private String ipctc;
    private String ipfep;
    private String endPointFep;
    private Integer arsStatusReady;
    private Integer autoUpdateDelay;
    private Integer trainDescriberSystem;
    private Integer timeTableManagement;
    private Integer trainDistanceGraph;
    private Integer automaticRouteSetting;
    private Integer automatiTrainRegulation;
    private String arsStatusEnable;
    private String arsCbiStatus;
    private String arsAtoStatus;
    private String autoUpdateActual;
    private String autoUpdateEnable;
    private String redudancy;
    private String mainBackup;
    private String nameRedudancy;
    private String gtidMe;
    private String gtidPartner;
    private String ipServerAccess;
    private String ipWorkstation1Access;
    private String ipWorkstation2Access;
    private String tdgPlan;
    private String tdgSch;

    private Date tdgDate;
    private String tdgBase;
    private String tdgRute;
    private String tdgLine;
    private String tdgPlanLive;
    private String tdgSchLive;
    private String tdgLineLive;
    private Date tdgDateLive;
    private String tdgBaseLive;
    private String tdgRuteLive;
    private Integer tdgRangeALive;
    private Integer tdgRangeBLive;
}

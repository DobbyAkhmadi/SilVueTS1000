package len.silvue.webpendukung.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "IDCONFIGURATION")
    private Integer id;

    @Column(name = "IPTMIS")
    private String iptmis;

    @Column(name = "REDUDANCYSTATUS")
    private String redudancyStatus;

    @Column(name = "IPCTC")
    private String ipctc;

    @Column(name = "IPFEP")
    private String ipfep;

    @Column(name = "ENDPOINTFEP")
    private String endPointFep;

    @Column(name = "ARSSTATUSREADY")
    private Integer arsStatusReady;

    @Column(name = "AUTOUPDATEDELAY")
    private Integer autoUpdateDelay;

    @Column(name = "TRAINDECRIBERSYSTEM")
    private Integer trainDescriberSystem;

    @Column(name = "TIMETABLEMANAGEMENT")
    private Integer timeTableManagement;

    @Column(name = "TRAINDISTANCEGRAPH")
    private Integer trainDistanceGraph;

    @Column(name = "AUTOMATICROUTESETTING")
    private Integer automaticRouteSetting;

    @Column(name = "AUTOMATICTRAINREGULATION")
    private Integer automatiTrainRegulation;

    @Column(name = "ARSSTATUSENABLE")
    private String arsStatusEnable;

    @Column(name = "ARSCBISTATUS")
    private String arsCbiStatus;

    @Column(name = "ARSATOSTATUS")
    private String arsAtoStatus;

    @Column(name = "AUTOUPDATEACTUAL")
    private String autoUpdateActual;

    @Column(name = "AUTOUPDATEENABLE")
    private String autoUpdateEnable;

    @Column(name = "REDUNDANCY")
    private String redudancy;

    @Column(name = "MAINBACKUP")
    private String mainBackup;

    @Column(name = "NAMEREDUNDANCY")
    private String nameRedudancy;

    @Column(name = "GTID_ME")
    private String gtidMe;

    @Column(name = "GTID_PARTNER")
    private String gtidPartner;

    @Column(name = "IPSERVERACCESS")
    private String ipServerAccess;

    @Column(name = "IPWORKSTATION1ACCESS")
    private String ipWorkstation1Access;

    @Column(name = "IPWORKSTATION2ACCESS")
    private String ipWorkstation2Access;

    @Column(name = "TDGPLAN")
    private String tdgPlan;

    @Column(name = "TDGSCH")
    private String tdgSch;

    @Column(name = "TDGLINE")
    private String tdgLine;

    @Column(name = "TDGDATE")
    @Temporal(TemporalType.DATE)
    private Date tdgDate;

    @Column(name = "TDGBASE")
    private String tdgBase;

    @Column(name = "TDGRUTE")
    private String tdgRute;

    @Column(name = "TDGPLANLIVE")
    private String tdgPlanLive;

    @Column(name = "TDGSCHLIVE")
    private String tdgSchLive;

    @Column(name = "TDGLINELIVE")
    private String tdgLineLive;

    @Temporal(TemporalType.DATE)
    @Column(name = "TDGDATELIVE")
    private Date tdgDateLive;

    @Column(name = "TDGBASELIVE")
    private String tdgBaseLive;

    @Column(name = "TDGRUTELIVE")
    private String tdgRuteLive;

    @Column(name = "TDGRANGEALIVE")
    private Integer tdgRangeALive;

    @Column(name = "TDGRANGEBLIVE")
    private Integer tdgRangeBLive;
}

package len.silvue.webpendukung.tmconfig.adapter.in.web;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class GapekaConfigCommand {
    private String plan;
    private String schedule;
    private String line;
    private Date date;
    private String base;
    private String rute;
    private String planLive;
    private String scheduleLive;
    private String lineLive;
    private Date dateLive;
    private String baseLive;
    private String ruteLive;
    private Integer rangeALive;
    private Integer rangeBLive;
}

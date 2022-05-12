package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.Station;

import java.util.List;

public interface SaveStationPort {
    List<Station> saveAllStation(List<Station> stationList) throws Exception;
}

package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.StationDto;
import len.silvue.webpendukung.utility.DataNotFoundException;

import java.util.List;

public interface FindStationUseCase {
    List<StationDto> getAllStation() throws Exception;
    List<StationDto> getAvailableStationMasterPlanByTrainAndTypeMasterPlanAndRuteRole(int idTrain,int idTypeMasterPlan,int idRoute) throws Exception;
    List<StationDto> getAllStationsNotInRouteDetail(int idRoute) throws Exception;
    List<StationDto> getAllStationsNotInLineDetail(int idLine) throws Exception;
    List<StationDto> getAllStationsNotInLineDetailById(int idDetail) throws Exception;
    List<StationDto> getAllStationsNotInRouteDetailById(int idDetail) throws Exception;
}

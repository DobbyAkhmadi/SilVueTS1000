package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.application.port.in.FindListLineDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.in.FindListRuteDetailUseCase;
import len.silvue.webpendukung.schedule.adapter.out.web.*;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.StationMapper;
import len.silvue.webpendukung.schedule.application.port.in.*;
import len.silvue.webpendukung.schedule.application.port.out.LoadStationPort;
import len.silvue.webpendukung.domains.Station;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindStationService implements FindStationUseCase {
    private final LoadStationPort loadStationPort;
    private final StationMapper stationMapper;
    private final FindListRuteDetailUseCase findListRuteDetailUseCase;
    private final FindListLineDetailUseCase findListLineDetailUseCase;
    private final FindTrainUseCase findTrainUseCase;
    private final FindTypeMasterPlanUseCase findTypeMasterPlanUseCase;
    private final FindRuteRoleUseCase findRuteRoleUseCase;
    int idRoutes=0;
    int idLines=0;
    @Override
    public List<StationDto> getAllStation() throws Exception {
        try {
            Optional<List<Station>> optionalStations = loadStationPort.loadAllStation();
            return stationMapper.toStationDtoList(optionalStations.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil seluruh station", e);
        }
    }
    @Override
    public List<StationDto> getAvailableStationMasterPlanByTrainAndTypeMasterPlanAndRuteRole(int idTrain,int idTypeMasterPlan,int idRoute) throws Exception {
        try {
            //get Type Schedule
            TypeMasterPlanDto typeMasterPlanDto = findTypeMasterPlanUseCase.getTypeMasterPlanById(idTypeMasterPlan);
            //get RuteRole
            RuteRoleDto ruteRoleDto = (RuteRoleDto) findRuteRoleUseCase.getAllRuteRole();

            //get Train
            TrainDto trainDto = findTrainUseCase.getTrainById(idTrain);
           //get station
            Optional<List<Station>> optionalStationDtoList = loadStationPort.loadAllStation();

            List<StationDto> stationDtoList = stationMapper.toStationDtoList(optionalStationDtoList.orElseThrow(DataNotFoundException::new));
            List<StationDto> stationDtoNewFilter = new ArrayList<>();
            // filter
//            stationDtoList.forEach(station -> {
//                boolean exists = false;
//                for(TrainDto trainDto : trainDtoList) {
//                    if(trainDto.getIdTrain() == station.getIdStation()) {
//                        exists = true;
//                        break;
//                    }
//                }
//                if(!exists) {
//                    stationDtoNewFilter.add(station);
//                }
//            });
            return stationDtoNewFilter;
            } catch(DataNotFoundException dfe) {
                throw new DataNotFoundException("failed to get station filter by not in master plan", dfe);
            } catch(Exception e) {
                throw new DataNotFoundException("Unknown error", e);
            }
    }
    @Override
    public List<StationDto> getAllStationsNotInRouteDetail(int idRoute) throws Exception {
        try {
            idRoutes=idRoute;
            //get stations
            Optional<List<Station>> optionalStationDtoList = loadStationPort.loadAllStation();
            //get route detail
            List<ListRuteDetailDto> listRuteDetailDtoList= findListRuteDetailUseCase.getAllListRuteDetailByRuteRoleId(idRoute);
            List<StationDto> stationDtoList = stationMapper.toStationDtoList(optionalStationDtoList.orElseThrow(DataNotFoundException::new));
            List<StationDto> stationDtoNewFilter = new ArrayList<>();
            // filter
            stationDtoList.forEach(station -> {
                    boolean exists = false;
                    for(ListRuteDetailDto listRuteDetailDto : listRuteDetailDtoList) {
                        if(listRuteDetailDto.getStation().getIdStation() == station.getIdStation()) {
                            exists = true;
                            break;
                        }
                    }
                    if(!exists) {
                        stationDtoNewFilter.add(station);
                    }
            });
            return stationDtoNewFilter;
        } catch(DataNotFoundException dfe) {
            throw new DataNotFoundException("failed to get station filter by not in route Line", dfe);
        } catch(Exception e) {
            throw new DataNotFoundException("Unknown error", e);
        }
    }
    @Override
    public List<StationDto> getAllStationsNotInLineDetail(int idLine) throws Exception {
        try {
            idLines=idLine;
            //get stations
            Optional<List<Station>> optionalStationDtoList = loadStationPort.loadAllStation();
            //get line detail
            List<ListLineDetailDto> listLineDetailDtoList = findListLineDetailUseCase.getAllListLineDetailByLineId(idLine);
            List<StationDto> stationDtoList = stationMapper.toStationDtoList(optionalStationDtoList.orElseThrow(DataNotFoundException::new));
            List<StationDto> stationDtoNewFilter = new ArrayList<>();
            // filter
            stationDtoList.forEach(station -> {
                boolean exists = false;
                for(ListLineDetailDto listLineDetailDto : listLineDetailDtoList) {
                    if(listLineDetailDto.getStation().getIdStation() == station.getIdStation()) {
                        exists = true;
                        break;
                    }
                }
                if(!exists) {
                    stationDtoNewFilter.add(station);
                }
            });
            return stationDtoNewFilter;
        } catch(DataNotFoundException dfe) {
            throw new DataNotFoundException("failed to get station filter by not in Line", dfe);
        } catch(Exception e) {
            throw new DataNotFoundException("Unknown error", e);
        }
    }
    @Override
    public List<StationDto> getAllStationsNotInLineDetailById(int idDetail) throws Exception {
        try {
            //get stations
            Optional<List<Station>> optionalStationDtoList = loadStationPort.loadAllStation();
            //get line detail
            List<ListLineDetailDto> listLineDetailDtoList= findListLineDetailUseCase.getAllListLineDetailByLineId(idLines);
            List<StationDto> stationDtoList = stationMapper.toStationDtoList(optionalStationDtoList.orElseThrow(DataNotFoundException::new));
            List<StationDto> stationDtoNewFilter = new ArrayList<>();
            // filter
            stationDtoList.forEach(station -> {
                boolean exists = false;
                for(ListLineDetailDto listLineDetailDto : listLineDetailDtoList) {
                    if(listLineDetailDto.getStation().getIdStation() == station.getIdStation()) {
                        exists = true;
                        break;
                    }
                }
                if(station.getIdStation()==idDetail)
                {
                    stationDtoNewFilter.add(station);
                }
                if(!exists) {
                    stationDtoNewFilter.add(station);
                }
            });
            return stationDtoNewFilter;
        } catch(DataNotFoundException dfe) {
            throw new DataNotFoundException("failed to get station filter by not in detail Line", dfe);
        } catch(Exception e) {
            throw new DataNotFoundException("Unknown error", e);
        }
    }
    @Override
    public List<StationDto> getAllStationsNotInRouteDetailById(int idDetail) throws Exception {
        //get stations
        Optional<List<Station>> optionalStationDtoList = loadStationPort.loadAllStation();
        List<ListRuteDetailDto> listRuteDetailDtoList= findListRuteDetailUseCase.getAllListRuteDetailByRuteRoleId(idRoutes);
        List<StationDto> stationDtoList = stationMapper.toStationDtoList(optionalStationDtoList.orElseThrow(DataNotFoundException::new));
        List<StationDto> stationDtoNewFilter = new ArrayList<>();
        stationDtoList.forEach(station -> {
            boolean exists = false;
            for(ListRuteDetailDto listRuteDetailDto : listRuteDetailDtoList) {
                if(listRuteDetailDto.getStation().getIdStation() == station.getIdStation()) {
                    exists = true;
                    break;
                }
            }
            if(station.getIdStation()==idDetail)
            {
                stationDtoNewFilter.add(station);
            }
            if(!exists) {
                stationDtoNewFilter.add(station);
            }
        });

        return stationDtoNewFilter;
    }
}

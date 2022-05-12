package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.domains.MasterPlan;
import len.silvue.webpendukung.domains.RuteRole;
import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.schedule.adapter.in.web.CombineMasterPlanCommand;
import len.silvue.webpendukung.schedule.adapter.in.web.ModifyMasterPlanCommand;
import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.TrainRepository;
import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.MasterPlanMapper;
import len.silvue.webpendukung.schedule.application.port.in.ModifyMasterPlanUseCase;
import len.silvue.webpendukung.schedule.application.port.out.*;
import len.silvue.webpendukung.utility.DataFormat;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModifyMasterPlanService implements ModifyMasterPlanUseCase {
    private final SaveMasterPlanPort saveMasterPlanPort;
    private final LoadMasterPlanPort loadMasterPlanPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final LoadTrainPort loadTrainPort;
    private final MasterPlanMapper masterPlanMapper;
    private final TrainRepository trainRepository;
    private final SaveScheduleTypePort saveScheduleTypePort;


    @Override
    public List<MasterPlanDto> modifyAllMasterPlan(ModifyMasterPlanCommand modifyMasterPlanCommand) throws Exception {
        try {
            // get all data master plan by id train
            Optional<List<MasterPlan>> optionalMasterPlan =
                    loadMasterPlanPort.loadMasterPlanByTrain(modifyMasterPlanCommand.getIdTrainSelected());
            List<MasterPlan> masterPlanList =  optionalMasterPlan.orElseThrow(DataNotFoundException::new);
            // get train
            Optional<Train> optionalTrain =
                    loadTrainPort.loadTrainById(modifyMasterPlanCommand.getIdTrain());
            Train train = optionalTrain.orElseThrow(DataNotFoundException::new);
            //get from master rute role
            Optional<RuteRole> optionalRuteRole =
                    loadRuteRolePort.
                            loadRuteRoleById(modifyMasterPlanCommand.getIdRuteRole());
            RuteRole ruteRole = optionalRuteRole.orElseThrow(DataNotFoundException::new);
            //get from master type master plan
            Optional<TypeMasterPlan> optionalTypeMasterPlan =
                    loadTypeMasterPlanPort.
                            loadTypeMasterPlanById(modifyMasterPlanCommand.getIdTypeMasterPlan());
            TypeMasterPlan typeMasterPlan = optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new);
            if (modifyMasterPlanCommand.getIdTrainAdd().isEmpty())
            {
                for(MasterPlan mp : masterPlanList) {
                    mp.setTrain(train);
                    mp.setRuteRole(ruteRole);
                    mp.setTypeMasterPlan(typeMasterPlan);
                }
            }
            else
            {
                // add when train not selected and add new from textbox
                Train train1 = Train.builder()
                        .noka(modifyMasterPlanCommand.getIdTrainAdd())
                        .build();
                train1 = trainRepository.save(train1);
                for(MasterPlan mp : masterPlanList) {
                    mp.setTrain(train1);
                    mp.setRuteRole(ruteRole);
                    mp.setTypeMasterPlan(typeMasterPlan);
                }
            }

            saveMasterPlanPort.storeMasterPlanList(masterPlanList);
            return masterPlanMapper.toMasterPlanDtoList(masterPlanList);
        } catch (Exception e) {
            throw new Exception("failed to modify all master plan", e);
        }
    }

    @Override
    public MasterPlanDto modifyMasterPlan(ModifyMasterPlanCommand modifyMasterPlanCommand) throws Exception {
        try {
            // get train
            Optional<Train> optionalTrain = loadTrainPort.loadTrainById(modifyMasterPlanCommand.getIdTrain());

            Optional<MasterPlan> optionalMasterPlan =
                    loadMasterPlanPort.loadMasterPlanById(modifyMasterPlanCommand.getIdMasterPlan());
            MasterPlan masterPlan =
                    optionalMasterPlan.orElseThrow(DataNotFoundException::new);

            Optional<RuteRole> optionalRuteRole =
                    loadRuteRolePort.loadRuteRoleById(modifyMasterPlanCommand.getIdRuteRole());
            RuteRole ruteRole =
                    optionalRuteRole.orElseThrow(DataNotFoundException::new);

            Optional<TypeMasterPlan> optionalTypeMasterPlan =
                    loadTypeMasterPlanPort.loadTypeMasterPlanById(modifyMasterPlanCommand.getIdTypeMasterPlan());
            TypeMasterPlan typeMasterPlan =
                    optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new);
            if (modifyMasterPlanCommand.getIdTrainAdd().isEmpty())
            {
                masterPlan.setTrain(optionalTrain.orElseThrow(null));
            }
            else
            {
                // add when train not selected and add new from Text Box
                Train train = Train.builder()
                        .noka(modifyMasterPlanCommand.getIdTrainAdd())
                        .build();
                train = trainRepository.save(train);
                masterPlan.setTrain(train);
            }
            masterPlan.setRuteRole(ruteRole);
            masterPlan.setTypeMasterPlan(typeMasterPlan);
            masterPlan.setArrival(DataFormat.changeStrTimeToDate(modifyMasterPlanCommand.getArrival()));
            masterPlan.setDepart(DataFormat.changeStrTimeToDate(modifyMasterPlanCommand.getDepart()));

            saveMasterPlanPort.storeMasterPlan(masterPlan);
            return masterPlanMapper.toMasterPlanDto(masterPlan);
        } catch (Exception e) {
            throw new Exception("failed to modify master plan", e);
        }
    }

    @Override
    public List<MasterPlanDto> combineMasterPlanByTypeMasterPlan(CombineMasterPlanCommand combineMasterPlanCommand) throws Exception {
        try {
            String idTypeMasterPlanArrStr = combineMasterPlanCommand.getIdTypeMasterPlan();
            String[] idTypeMasterPlans = idTypeMasterPlanArrStr.split(",");
            if(idTypeMasterPlans == null || idTypeMasterPlans.length == 0)
                return new ArrayList<>();
            List<MasterPlan> masterPlansToAdd = new ArrayList<>();
            for(int i = 0; i < idTypeMasterPlans.length; i++) {
                int idTypeMasterPlan = Integer.parseInt(idTypeMasterPlans[i]);
                Optional<List<MasterPlan>> optionalMasterPlans = loadMasterPlanPort.loadAllMasterPlanByTypeMasterPlan(idTypeMasterPlan);
                List<MasterPlan> masterPlans = optionalMasterPlans.orElse(new ArrayList<>());
                List<MasterPlan> tempListToAdd = masterPlans.stream()
                        .filter(masterPlan -> {
                            if(masterPlansToAdd.isEmpty()) {
                                return true;
                            } else {
                                boolean isAdd = true;
                                for(MasterPlan masterPlanToAdd : masterPlansToAdd) {
                                    String stationToAdd = masterPlanToAdd.getPeronFrom().getStation().getNameStation();
                                    String stationPlan = masterPlan.getPeronFrom().getStation().getNameStation();
                                    Date arrivalToAdd = masterPlanToAdd.getArrival();
                                    Date arrivalPlan = masterPlan.getArrival();
                                    Date departToAdd = masterPlanToAdd.getDepart();
                                    Date departPlan = masterPlan.getDepart();

                                    if(stationToAdd.compareToIgnoreCase(stationPlan) == 0
                                            && (arrivalToAdd.compareTo(arrivalPlan) == 0
                                            || departToAdd.compareTo(departPlan) == 0)) {
                                        isAdd = false;
                                        break;
                                    }
                                }
                                return isAdd;
                            }
                        }).collect(Collectors.toList());
                masterPlansToAdd.addAll(tempListToAdd);
            }

            if(!masterPlansToAdd.isEmpty()) {
                TypeMasterPlan typeMasterPlan = TypeMasterPlan.builder()
                        .nameTypeMasterPlan(combineMasterPlanCommand.getNewNameTypeMasterPlan())
                        .build();
                Optional<TypeMasterPlan> optionalTypeMasterPlan = saveScheduleTypePort.storeScheduleType(typeMasterPlan);
                typeMasterPlan = optionalTypeMasterPlan.orElse(null);
                List<MasterPlan> masterPlanListNew = new ArrayList<>();
                for(MasterPlan masterPlan : masterPlansToAdd) {
                    MasterPlan masterPlanNew = MasterPlan.builder()
                            .typeMasterPlan(typeMasterPlan)
                            .peronFrom(masterPlan.getPeronFrom())
                            .peronTo(masterPlan.getPeronTo())
                            .flagMasterPlan(masterPlan.getFlagMasterPlan())
                            .numberTrain(masterPlan.getNumberTrain())
                            .train(masterPlan.getTrain())
                            .flagCheckConflict(masterPlan.getFlagCheckConflict())
                            .depart(masterPlan.getDepart())
                            .arrival(masterPlan.getArrival())
                            .ruteRole(masterPlan.getRuteRole())
                            .dwellingTime(masterPlan.getDwellingTime())
                            .ruteRole(masterPlan.getRuteRole())
                            .build();
                    masterPlanListNew.add(masterPlanNew);
                }
                saveMasterPlanPort.storeMasterPlanList(masterPlanListNew);
                return masterPlanMapper.toMasterPlanDtoList(masterPlanListNew);
            } else {
                return new ArrayList<>();
            }
        } catch(Exception e) {
            throw new Exception("Gagal mengkombinasi master plan", e);
        }
    }

}

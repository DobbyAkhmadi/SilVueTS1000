package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.MasterPlanMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindMasterPlanUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTrainPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.domains.MasterPlan;
import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindMasterPlanService implements FindMasterPlanUseCase {
    private final LoadMasterPlanPort loadMasterPlanPort;
    private final LoadTrainPort loadTrainPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final MasterPlanMapper masterPlanMapper;

    @Transactional

    @Override
    public List<MasterPlanDto> getAllMasterPlan() throws Exception {
        try {
            Optional<List<MasterPlan>> optionalMasterPlan = loadMasterPlanPort.loadAllMasterPlan();
            return masterPlanMapper.toMasterPlanDtoList(optionalMasterPlan.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Data Masterplan kosong", e);
        }
    }

    @Override
    public List<MasterPlanDto> getAllMasterPlanByTypeMasterPlan(int id) throws Exception {
        try {
            Optional<List<MasterPlan>> optionalMasterPlan = loadMasterPlanPort.loadAllMasterPlanByTypeMasterPlan(id);
            return masterPlanMapper.toMasterPlanDtoList(optionalMasterPlan.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Data Masterplan kosong", e);
        }
    }

    @Override
    public MasterPlanDto getMasterPlanById(int masterPlanId) throws DataNotFoundException {
        try {
            Optional<MasterPlan> optionalMasterPlan = loadMasterPlanPort.loadMasterPlanById(masterPlanId);
            return masterPlanMapper.toMasterPlanDto(optionalMasterPlan.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Gagal mengambil schedule berdasarkan id", e);
        }
    }

    @Override
    public MasterPlanDto getHeadMasterPlan(int id) throws DataNotFoundException {
        try {
            Optional<MasterPlan> optionalMasterPlan = loadMasterPlanPort.loadMasterPlanByTypeMasterPlan(id);
            return masterPlanMapper.toMasterPlanDto(optionalMasterPlan.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Gagal mengambil schedule berdasarkan id", e);
        }
    }
    @Override
    public List<MasterPlanDto> getMasterPlanByTrainIdAndTypeMasterPlanId(int trainId, int typeMasterPlanId) throws Exception {
        try {
            Optional<Train> optionalTrain = loadTrainPort.loadTrainById(trainId);
            Optional<TypeMasterPlan> optionalTypeMasterPlan = loadTypeMasterPlanPort.loadTypeMasterPlanById(typeMasterPlanId);
            Optional<List<MasterPlan>> optionalMasterPlans = loadMasterPlanPort
                    .loadMasterPlanByTrainAndTypeMasterPlan(optionalTrain.orElseThrow(DataNotFoundException::new),
                            optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new));
            return masterPlanMapper.toMasterPlanDtoList(optionalMasterPlans.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil service get master plan by train id and type master plan id", e);
        }
    }
}

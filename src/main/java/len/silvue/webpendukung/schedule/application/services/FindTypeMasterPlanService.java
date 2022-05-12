package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.TypeMasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.TypeMasterPlanMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindTypeMasterPlanUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindTypeMasterPlanService implements FindTypeMasterPlanUseCase {
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final TypeMasterPlanMapper typeMasterPlanMapper;


    @Override
    public List<TypeMasterPlanDto> getAllTypeMasterPlan() throws Exception {
        try {
            Optional<List<TypeMasterPlan>> optionalTypeMasterPlans = loadTypeMasterPlanPort.loadAllTypeMasterPlan();
            return typeMasterPlanMapper.toTypeMasterPlanDtoList(optionalTypeMasterPlans.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengeksekusi service ambil semua type master plan", e);
        }
    }

    @Override
    public TypeMasterPlanDto getTypeMasterPlanById(int id) throws Exception {
        try {
            Optional<TypeMasterPlan> optionalTypeMasterPlan = loadTypeMasterPlanPort.loadTypeMasterPlanById(id);
            return typeMasterPlanMapper.toTypeMasterPlanDto(optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data type master plan by id", e);
        }
    }
}

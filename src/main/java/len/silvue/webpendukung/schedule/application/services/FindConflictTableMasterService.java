package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.ConflictMasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.ConflictMasterPlanMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindConflictTableMasterUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadConflictTableMasterPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadSuccessBrowsePort;
import len.silvue.webpendukung.domains.ConflictTableMaster;
import len.silvue.webpendukung.domains.SuccessBrowse;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindConflictTableMasterService implements FindConflictTableMasterUseCase {
    private final LoadConflictTableMasterPort loadConflictTableMasterPort;
    private final LoadSuccessBrowsePort loadSuccessBrowsePort;
    private final ConflictMasterPlanMapper conflictMasterPlanMapper;
    @Override
    public List<ConflictMasterPlanDto> loadAllConflict() throws Exception {
        try {
            Optional<List<ConflictTableMaster>> optionalConflictTableMaster = Optional.empty();
            Optional<SuccessBrowse> optionalSuccessBrowse = loadSuccessBrowsePort.loadSuccessBrowse(1);
            SuccessBrowse successBrowse = optionalSuccessBrowse.orElseThrow(DataNotFoundException::new);
            if (successBrowse.getSuccessNotification()==1)
            {
                optionalConflictTableMaster = loadConflictTableMasterPort.loadAllConflict();
            }
            return conflictMasterPlanMapper.toConflictMasterPlanDto(optionalConflictTableMaster.orElse(new ArrayList<>()));
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("failed to get all conflict master plan", e);
        }
    }
}

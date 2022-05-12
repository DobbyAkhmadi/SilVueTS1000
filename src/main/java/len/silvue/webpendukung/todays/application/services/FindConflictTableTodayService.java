package len.silvue.webpendukung.todays.application.services;

import len.silvue.webpendukung.todays.adapter.out.web.ConflictTodayDto;
import len.silvue.webpendukung.todays.application.port.in.FindConflictTableTodayUseCase;
import len.silvue.webpendukung.domains.ConflictTableToday;
import len.silvue.webpendukung.domains.SuccessBrowseConflict;
import len.silvue.webpendukung.todays.adapter.out.web.mapper.ConflictTodayMapper;
import len.silvue.webpendukung.todays.application.port.out.LoadConflictTableTodayPort;
import len.silvue.webpendukung.todays.application.port.out.LoadSuccessPort;
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
public class FindConflictTableTodayService implements FindConflictTableTodayUseCase {
    private final LoadConflictTableTodayPort loadConflictTableTodayPort;
    private final LoadSuccessPort loadSuccessPort;
    private final ConflictTodayMapper conflictTodayMapper;
    @Override
    public List<ConflictTodayDto> loadAllConflict() throws Exception {
        try {
            Optional<List<ConflictTableToday>> optionalConflictTableMaster = Optional.empty();
            Optional<SuccessBrowseConflict> optionalSuccessBrowse = loadSuccessPort.loadSuccessBrowse(1);
            SuccessBrowseConflict successBrowse = optionalSuccessBrowse.orElseThrow(DataNotFoundException::new);
            if (successBrowse.getSuccessNotification()==1)
            {
                optionalConflictTableMaster = loadConflictTableTodayPort.loadAllConflict();
            }
            return conflictTodayMapper.toConflictMasterPlanDto(optionalConflictTableMaster.orElse(new ArrayList<>()));
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("failed to get all conflict today detail", e);
        }
    }
}

package len.silvue.webpendukung.setting.application.services;


import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.mapper.ListLineDetailMapper;
import len.silvue.webpendukung.gapeka.adapter.out.web.mapper.ListRuteDetailMapper;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListLineDetailPort;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListRuteDetailPort;
import len.silvue.webpendukung.domains.ListLineDetail;
import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.setting.application.port.in.FindSettingUseCase;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class SettingServices implements FindSettingUseCase {
    private final ListRuteDetailMapper listRuteDetailMapper;
    private final ListLineDetailMapper listLineDetailMapper;
    private final LoadListRuteDetailPort loadListRuteDetailPort;
    private final LoadListLineDetailPort loadListLineDetailPort;
    @Override
    public List<ListRuteDetailDto> getAllRouteSettingDetail() throws Exception {
        try {
            Optional<List<ListRuteDetail>> optionalListRuteDetailList = loadListRuteDetailPort.loadAllListRuteDetail();
            return listRuteDetailMapper.toListRuteDetailDtoList(optionalListRuteDetailList.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Data all route setting detail null", e);
        }
    }

    @Override
    public List<ListLineDetailDto> getAllLineSettingDetail() throws Exception {
        try {
            Optional<List<ListLineDetail>> optionalListRuteDetailList = loadListLineDetailPort.loadAllListLineDetail();
            return listLineDetailMapper.toListLineDetailDtoList(optionalListRuteDetailList.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Data all route setting detail null", e);
        }
    }
}

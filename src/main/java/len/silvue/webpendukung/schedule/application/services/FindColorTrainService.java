package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.ColorTrainDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.ColorTrainMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindColorTrainUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadColorTrainPort;
import len.silvue.webpendukung.domains.ColorTrain;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindColorTrainService implements FindColorTrainUseCase {
    private final LoadColorTrainPort loadColorTrainPort;
    private final ColorTrainMapper colorTrainMapper;

    @Override
    public List<ColorTrainDto> getAllTrainColor() throws Exception {
        try {
            Optional<List<ColorTrain>> optionalColorTrainList = loadColorTrainPort.loadAllColorTrain();
            return colorTrainMapper.toColorTrainDtoList(optionalColorTrainList.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua color train", e);
        }
    }

    @Override
    public ColorTrainDto getColorTrainById(int id) throws Exception {
        try {
            Optional<ColorTrain> optionalColorTrain = loadColorTrainPort.loadColorTrainById(id);
            return colorTrainMapper.toColorTrainDto(optionalColorTrain.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data color train by id", e);
        }
    }
}

package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.in.web.ModifyColorTrainCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.ColorTrainDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.ColorTrainMapper;
import len.silvue.webpendukung.schedule.application.port.in.ModifyColorTrainUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadColorTrainPort;
import len.silvue.webpendukung.schedule.application.port.out.SaveColorTrainPort;
import len.silvue.webpendukung.domains.ColorTrain;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModifyColorTrainService implements ModifyColorTrainUseCase {
    private final SaveColorTrainPort saveColorTrainPort;
    private final LoadColorTrainPort loadColorTrainPort;
    private final ColorTrainMapper colorTrainMapper;

    @Override
    public ColorTrainDto modifyColorTrain(ModifyColorTrainCommand modifyColorTrainCommand) throws Exception {
        try {
            Optional<ColorTrain> optionalColorTrain = loadColorTrainPort.loadColorTrainById(modifyColorTrainCommand.getIdColorTrain());

            ColorTrain colorTrain = optionalColorTrain.orElseThrow(DataNotFoundException::new);
            colorTrain.setColorTrain(modifyColorTrainCommand.getColorTrain());

            saveColorTrainPort.storeColorTrain(colorTrain);
            return colorTrainMapper.toColorTrainDto(colorTrain);
        } catch (Exception e) {
            throw new Exception("Gagal mengubah color train", e);
        }
    }
}

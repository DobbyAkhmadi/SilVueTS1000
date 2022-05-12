package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.ColorTrain;

import java.util.List;

public interface SaveColorTrainPort {
    ColorTrain storeColorTrain(ColorTrain colorTrain) throws Exception;
    List<ColorTrain> saveAllColorTrain(List<ColorTrain> colorTrainList) throws Exception;
}

package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.PeronDto;

import java.util.List;

public interface FindPeronUseCase {
    List<PeronDto> getPeronListByStation(Integer idStation) throws Exception;
    PeronDto getPeronById(Integer idStation) throws Exception;
}

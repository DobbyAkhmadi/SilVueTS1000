package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.PeronDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.PeronMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindPeronUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadPeronPort;
import len.silvue.webpendukung.domains.Peron;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindPeronService implements FindPeronUseCase {
    private final LoadPeronPort loadPeronPort;
    private final PeronMapper mapper;

    @Override
    public List<PeronDto> getPeronListByStation(Integer idStation) throws Exception {
        try {
            Optional<List<Peron>> optionalPerons = loadPeronPort.loadPeronsByStation(idStation);
            return mapper.toPeronDtoList(optionalPerons.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("service mengambil peron list gagal", e);
        }
    }

    @Override
    public PeronDto getPeronById(Integer idStation) throws Exception {
        try {
            Optional<Peron> optionalPeronDto = loadPeronPort.loadPeronById(idStation);
            return mapper.toPeronDto(optionalPeronDto.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil peron by Id");
        }
    }
}

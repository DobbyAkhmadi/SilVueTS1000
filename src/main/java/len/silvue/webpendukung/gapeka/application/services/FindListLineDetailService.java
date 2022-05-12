package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.LineDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.mapper.LineMapper;
import len.silvue.webpendukung.gapeka.adapter.out.web.mapper.ListLineDetailMapper;
import len.silvue.webpendukung.gapeka.application.port.in.FindListLineDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListLineDetailPort;
import len.silvue.webpendukung.domains.Line;
import len.silvue.webpendukung.domains.ListLineDetail;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindListLineDetailService implements FindListLineDetailUseCase {
    private final LoadListLineDetailPort loadListLineDetailPort;
    private final ListLineDetailMapper listLineDetailMapper;
    private final LineMapper lineMapper;

    @Override
    public List<ListLineDetailDto> getAllListLineDetail() throws Exception {
        try {
            Optional<List<ListLineDetail>> optionalListLineDetailDtoList = loadListLineDetailPort.loadAllListLineDetail();
            return listLineDetailMapper.toListLineDetailDtoList(optionalListLineDetailDtoList.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua list line detail", e);
        }
    }

    @Override
    public List<ListLineDetailDto> getAllListLineDetailByLineId(int idLine) throws Exception {
        try {
            Optional<List<ListLineDetail>> optionalListLineDetailList = loadListLineDetailPort.loadListLineDetailByLineId(idLine);
            return listLineDetailMapper.toListLineDetailDtoList(optionalListLineDetailList.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil list line detail by line", e);
        }
    }

    @Override
    public ListLineDetailDto getListLineDetailById(int idLine) throws Exception {
        try {
            Optional<ListLineDetail> optionalListLineDetail = loadListLineDetailPort.loadListLineDetailById(idLine);
            return listLineDetailMapper.toListLineDetailDto(optionalListLineDetail.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("failed get list line detail by id", e);
        }
    }

    @Override
    public List<LineDto> getAllListLineDetailByDistinctLine() throws Exception {
        try {
            Optional<List<Line>> optionalLines = loadListLineDetailPort.loadAllListLineDetailByDistinctLine();
            return lineMapper.toLineDtoList(optionalLines.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("failed to get list line detail distinct", e);
        }
    }
}

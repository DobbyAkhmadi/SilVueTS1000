package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.mapper.ListRuteDetailMapper;
import len.silvue.webpendukung.gapeka.application.port.in.FindListRuteDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListRuteDetailPort;
import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.schedule.adapter.out.web.RuteRoleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.RuteRoleMapper;
import len.silvue.webpendukung.domains.RuteRole;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindListRuteDetailService implements FindListRuteDetailUseCase {
    private final LoadListRuteDetailPort loadListRuteDetailPort;
    private final ListRuteDetailMapper listRuteDetailMapper;
    private final RuteRoleMapper ruteRoleMapper;

    @Override
    public List<ListRuteDetailDto> getAllListRuteDetail() throws Exception {
        try {
            Optional<List<ListRuteDetail>> optionalListRuteDetail = loadListRuteDetailPort.loadAllListRuteDetail();
            return listRuteDetailMapper.toListRuteDetailDtoList(optionalListRuteDetail.orElse(null));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua list rute detail", e);
        }
    }

    @Override
    public List<ListRuteDetailDto> getAllListRuteDetailByRuteRoleId(int ruteRoleId) throws Exception {
        try {
            Optional<List<ListRuteDetail>> optionalListRuteDetails = loadListRuteDetailPort.loadListRuteDetailByRuteRoleId(ruteRoleId);
            return listRuteDetailMapper.toListRuteDetailDtoList(optionalListRuteDetails.orElse(null));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil list rute detail by rute role", e);
        }
    }

    @Override
    public ListRuteDetailDto getListRuteDetailById(int idRuteDetail) throws Exception {
        try {
            Optional<ListRuteDetail> optionalListRuteDetail = loadListRuteDetailPort.loadListRuteDetailById(idRuteDetail);
            return listRuteDetailMapper.toListRuteDetailDto(optionalListRuteDetail.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Gagal mengambil schedule berdasarkan id", e);
        }
    }

    @Override
    public List<RuteRoleDto> getAllListRuteDetailByDistinctRuteRole() throws Exception {
        try {
            Optional<List<RuteRole>> optionalListRuteDetail = loadListRuteDetailPort.loadAllListRuteDetailByDistinctRuteRole();
            return ruteRoleMapper.toRuteRoleDtoList(optionalListRuteDetail.orElse(null));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua list rute detail distinct", e);
        }
    }


}

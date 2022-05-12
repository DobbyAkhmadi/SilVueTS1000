package len.silvue.webpendukung.ars.adapter.out.web.mapper;

import len.silvue.webpendukung.ars.adapter.out.web.ArsConflictDto;
import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.domains.ArsConflict;
import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.schedule.adapter.out.web.ConflictMasterPlanDto;
import len.silvue.webpendukung.domains.ConflictTableMaster;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConflictArsMapper {
    ConflictArsMapper MAPPER = Mappers.getMapper(ConflictArsMapper.class);

    ArsConflictDto toConflictAtrPlanDto(ArsSchedule arsConflict);
    ConflictTableMaster toConflictTableMaster(ArsConflictDto arsConflictDto);
    List<ArsConflictDto> toConflictArsDto(List<ArsConflict> arsScheduleList);
    List<ArsScheduleDto> toConflictArsDtoList(List<ArsSchedule> arsScheduleList);
}

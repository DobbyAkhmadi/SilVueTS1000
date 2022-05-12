package len.silvue.webpendukung.actual.application.port.in;

import len.silvue.webpendukung.actual.adapter.out.web.DepartementDto;


import java.util.List;

public interface FindDepartementUseCase {
    List<DepartementDto> getAllDepartement() throws Exception;
    DepartementDto getDepartementById(int id) throws Exception;
}

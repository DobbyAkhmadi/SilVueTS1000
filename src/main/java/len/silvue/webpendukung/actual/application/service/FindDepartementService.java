package len.silvue.webpendukung.actual.application.service;

import len.silvue.webpendukung.actual.adapter.out.web.DepartementDto;
import len.silvue.webpendukung.actual.adapter.out.web.mapper.DepartementMapper;
import len.silvue.webpendukung.actual.application.port.in.FindDepartementUseCase;
import len.silvue.webpendukung.actual.application.port.out.LoadDepartementPort;

import len.silvue.webpendukung.domains.Departement;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindDepartementService implements FindDepartementUseCase {
    private final LoadDepartementPort loadDepartementPort;
    private final DepartementMapper departementMapper;

    @Override
    public List<DepartementDto> getAllDepartement() throws Exception {
        try {
            Optional<List<Departement>> optionalDepartemens = loadDepartementPort.loadAllDepartement();
            return departementMapper.toDepartementDtoList(optionalDepartemens.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengeksekusi service ambil semua departement", e);
        }
    }

    @Override
    public DepartementDto getDepartementById(int id) throws Exception {
        try {
            Optional<Departement> optionalDepartement = loadDepartementPort.loadDepartementById(id);
            return departementMapper.toDepartementDto(optionalDepartement.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data departement by id", e);
        }
    }
}

package len.silvue.webpendukung.gapeka.adapter.out.persistence;

import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.LineRepository;
import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.ListLineDetailRepository;
import len.silvue.webpendukung.gapeka.application.port.out.*;
import len.silvue.webpendukung.domains.Line;
import len.silvue.webpendukung.domains.ListLineDetail;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ListLineDetailPersistenceAdapter implements LoadListLineDetailPort, SaveListLineDetailPort ,DeleteListLineDetailPort{
    private final ListLineDetailRepository listLineDetailRepository;
    private final LineRepository lineRepository;
    @Override
    public Optional<List<ListLineDetail>> loadAllListLineDetail() throws Exception {
        try {
            List<ListLineDetail> listLineDetailList = listLineDetailRepository.findAllOrderByIdLineAndIndex();
            return Optional.of(listLineDetailList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil all list detail", e);
        }
    }

    @Override
    public Optional<List<ListLineDetail>> loadListLineDetailByLineId(int idLine) throws Exception {
        try {
            Optional<Line> optionalLine = lineRepository.findById(idLine);
            List<ListLineDetail> listLineDetailList = listLineDetailRepository.findListLineDetailsByLine(optionalLine.orElseThrow(DataNotFoundException::new));
            return Optional.of(listLineDetailList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil list line detail by line", e);
        }
    }

    @Override
    public Optional<ListLineDetail> loadListLineDetailById(int idLine) throws Exception {
        try {
            Optional<ListLineDetail> listLineDetail = listLineDetailRepository.findById(idLine);
            return listLineDetail;
        } catch(Exception e) {
            throw new Exception("Gagal mengambil all list rute detail", e);
        }
    }

    @Override
    public Optional<List<Line>> loadAllListLineDetailByDistinctLine() throws Exception {
        try {
            return Optional.of(listLineDetailRepository.findDistinctLinesFromListLineDetail());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil all list rute distinct detail", e);
        }
    }

    @Override
    public ListLineDetail saveListLine(ListLineDetail listLineDetail) throws Exception {
        try {
            ListLineDetail saveListLineDetail=listLineDetailRepository.save(listLineDetail);
            return saveListLineDetail;
        } catch (Exception e) {
            throw new Exception("failed to save list Line detail ", e);
        }
    }

    @Override
    public ListLineDetail storeListLine(ListLineDetail listLineDetail) throws Exception {
        try {
            ListLineDetail updateLineDetail=listLineDetailRepository.save(listLineDetail);
            return updateLineDetail;
        } catch (Exception e) {
            throw new Exception("failed to save list Line detail ", e);
        }
    }

    @Override
    public void eraseAllListLineDetail() throws Exception {
        try {
            listLineDetailRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("Gagal mengapus semua list line detail", e);
        }
    }

    @Override
    public void eraseListLine(int idLineList) throws Exception {
        try {
            listLineDetailRepository.deleteById(idLineList);
        } catch(Exception e) {
            throw new Exception("failed to erase list line", e);
        }
    }

    @Override
    public void eraseAllListLine(int idLineList) throws Exception {
        try {
            listLineDetailRepository.deleteListLineDetailByLine(idLineList);
        } catch (Exception e) {
            throw new Exception("failed to erase all list line", e);
        }
    }

    @Override
    public List<ListLineDetail> storeAllListLine(List<ListLineDetail> listLineDetailList) throws Exception {
        try {
            return listLineDetailRepository.saveAll(listLineDetailList);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan listlinedetail", e);
        }
    }

    @Override
    public Optional<List<ListLineDetail>> loadAllListByNameLine(String nameLine) throws Exception {
        try {
            return Optional.of(listLineDetailRepository.findAllByNameLine(nameLine));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil listlinedetail berdasarkan nameline", e);
        }
    }
}

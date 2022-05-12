package len.silvue.webpendukung.gapeka.adapter.out.persistence;

import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.ListRuteDetailRepository;
import len.silvue.webpendukung.gapeka.application.port.out.DeleteListRuteDetailPort;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListRuteDetailPort;
import len.silvue.webpendukung.gapeka.application.port.out.SaveListRuteDetailPort;
import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.RuteRoleRepository;
import len.silvue.webpendukung.domains.RuteRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ListRuteDetailPersistenceAdapter implements LoadListRuteDetailPort, DeleteListRuteDetailPort, SaveListRuteDetailPort {
    private final ListRuteDetailRepository listRuteDetailRepository;
    private final RuteRoleRepository ruteRoleRepository;

    @Override
    public Optional<List<ListRuteDetail>> loadAllListRuteDetail() throws Exception {
        try {
            List<ListRuteDetail> listRuteDetailList = listRuteDetailRepository.findAllOrderByIdLineAndIndex();
            return Optional.of(listRuteDetailList);
        } catch (Exception e) {
            throw new Exception("Gagal mengambil all list rute detail", e);
        }
    }

    @Override
    public Optional<List<ListRuteDetail>> loadListRuteDetailByRuteRoleId(int ruteRoleId) throws Exception {
        try {
            Optional<RuteRole> optionalRuteRole = ruteRoleRepository.findById(ruteRoleId);
            List<ListRuteDetail> listRuteDetailList = listRuteDetailRepository.findListRuteDetailsByRuteRole(optionalRuteRole.orElse(null));
            return Optional.of(listRuteDetailList);
        } catch (Exception e) {
            throw new Exception("Gagal mengambil listrutedetail by ruteroleid", e);
        }
    }

    @Override
    public Optional<ListRuteDetail> loadListRuteDetailById(int idDetail) throws Exception {
        try {
            Optional<ListRuteDetail> listRuteDetail = listRuteDetailRepository.findById(idDetail);
            return listRuteDetail;
        } catch (Exception e) {
            throw new Exception("Gagal mengambil all list rute detail", e);
        }
    }

    @Override
    public Optional<List<RuteRole>> loadAllListRuteDetailByDistinctRuteRole() throws Exception {
        try {
            return Optional.of(listRuteDetailRepository.findDistinctRuteRolesFromListRuteDetail());
        } catch (Exception e) {
            throw new Exception("Gagal mengambil all list rute distinct detail", e);
        }
    }

    @Override
    public Optional<List<ListRuteDetail>> loadListRuteDetailByRuteRoleName(String ruteRoleName) throws Exception {
        try {
            List<ListRuteDetail> listRuteDetailList = listRuteDetailRepository.findListRuteDetailByRuteRoleName(ruteRoleName);
            return Optional.of(listRuteDetailList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil list rute detail berdasarkan nama rute role", e);
        }
    }

    @Override
    public void eraseListRoute(int idRouteList) throws Exception {
        try {
            listRuteDetailRepository.deleteById(idRouteList);
        } catch (Exception e) {
            throw new Exception("failed to delete list route detail ", e);
        }
    }

    @Override
    public void eraseAllListRoute(int idRouteList) throws Exception {
        try {
            listRuteDetailRepository.deleteListRuteDetailByRuteRole(idRouteList);
        } catch (Exception e) {
            throw new Exception("failed to get list route detail ", e);
        }
    }

    @Override
    public ListRuteDetail saveListRoute(ListRuteDetail listRuteDetail) throws Exception {
        try {
            ListRuteDetail saveListRuteDetail = listRuteDetailRepository.save(listRuteDetail);
            return saveListRuteDetail;
        } catch (Exception e) {
            throw new Exception("failed to save list route detail ", e);
        }
    }

    @Override
    public ListRuteDetail storeListRoute(ListRuteDetail listRuteDetail) throws Exception {
        try {
            ListRuteDetail updateRuteDetail = listRuteDetailRepository.save(listRuteDetail);
            return updateRuteDetail;
        } catch (Exception e) {
            throw new Exception("failed to save list route detail ", e);
        }
    }

    @Override
    public void eraseAllListRuteDetail() throws Exception {
        try {
            listRuteDetailRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("Gagal menghapus semua listrutedetail", e);
        }
    }

    @Override
    public List<ListRuteDetail> storeAllListRoute(List<ListRuteDetail> listRuteDetailList) throws Exception {
        try {
            return listRuteDetailRepository.saveAll(listRuteDetailList);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan listrutedetail", e);
        }
    }
}

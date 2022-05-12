package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.RouteRepository;
import len.silvue.webpendukung.schedule.application.port.out.DeleteRoutePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadRoutePort;
import len.silvue.webpendukung.schedule.application.port.out.SaveRoutePort;
import len.silvue.webpendukung.domains.Route;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoutePersistenceAdapter implements LoadRoutePort, SaveRoutePort, DeleteRoutePort {
    private final RouteRepository routeRepository;
    @Override
    public Optional<List<Route>> loadAllRoute() throws Exception {
        try {
            return Optional.of(routeRepository.findAll());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua route", e);
        }
    }
    @Override
    public Optional<Route> loadRouteById(int id) throws DataNotFoundException {
        try {
            return routeRepository.findById(id);
        } catch(Exception e) {
            throw new DataNotFoundException("Data route kosong", e);
        }
    }

    @Override
    public void saveRoute(Route route) throws Exception {
        try {
            routeRepository.save(route);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan route", e);
        }
    }

    @Override
    public void eraseAllRoute() throws Exception {
        try {
            routeRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("Gagal menghapus semua route", e);
        }
    }
}

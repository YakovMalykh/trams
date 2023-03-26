package metro.trams.servicies.interfacies;

import metro.trams.entity.Route;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoutesService {

    List<Route> getAllRoutes();

    ResponseEntity<Route> getRouteById(Long id);

    ResponseEntity<Long> saveIntoDb(String code, String name);

    ResponseEntity<?> deleteById(Long id);

    ResponseEntity<Route> updateRoute(Long id, String code, String name);
}

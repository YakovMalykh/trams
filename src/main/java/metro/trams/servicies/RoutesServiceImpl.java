package metro.trams.servicies;

import lombok.extern.slf4j.Slf4j;
import metro.trams.dto.RouteDto;
import metro.trams.exceptions.NoSuchRouteException;
import metro.trams.exceptions.RouteAlreadyExists;
import metro.trams.mappers.MapperDto;
import metro.trams.entity.Route;
import metro.trams.repositories.RoutesRepository;
import metro.trams.servicies.interfacies.RoutesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoutesServiceImpl implements RoutesService {
    private final RoutesRepository routesRepo;
    private final MapperDto mapper;

    public RoutesServiceImpl(RoutesRepository routesRepo, MapperDto mapper) {
        this.routesRepo = routesRepo;
        this.mapper = mapper;
    }


    @Override // добавить пагинацию при большом количестве
    public List<Route> getAllRoutes() {
        return routesRepo.findAll();
    }

    @Override
    public ResponseEntity<Route> getRouteById(Long id) {
        Route route = routesRepo.findById(id).orElseThrow(() ->
                new NoSuchRouteException(String.format("Mаршрут по id %s не найден", id)));
        return ResponseEntity.ok(route);
    }

    @Override
    public ResponseEntity<Long> saveIntoDb(String code, String name) {
        isRouteAlredyExists(code, name);

        Route route = new Route(code, name);
        Route savedRoute = routesRepo.save(route);

        return ResponseEntity.ok(savedRoute.getId());
    }



    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Route route = routesRepo.findById(id).orElseThrow(() ->
                new NoSuchRouteException(String.format("Mаршрут по id %s не найден", id)));
        routesRepo.delete(route);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Route> updateRoute(Long id, String code, String name) {
        RouteDto dto = new RouteDto(code, name);
        Route routeFromDb = routesRepo.findById(id).orElseThrow(() ->
                new NoSuchRouteException(String.format("Mаршрут по id %s не найден", id)));
        mapper.updateRouteFromDto(dto, routeFromDb);

        Route updated = routesRepo.save(routeFromDb);

        return ResponseEntity.ok(updated);
    }



    /**
     * проверяет существуют ли в бд такие код и имя, если существует выбрасывает исключение
     * @param code
     * @param name
     * @throw RouteAlreadyExists
     */
    private void isRouteAlredyExists(String code, String name) {
        boolean codeIsPresent = routesRepo.findByCodeIgnoreCase(code).isPresent();
        boolean nameIsPresent = routesRepo.findByNameIgnoreCase(name).isPresent();
        if (codeIsPresent || nameIsPresent) {
            throw new RouteAlreadyExists(String
                    .format("Маршрут с таким кодом %s или именем %s уже существует", code, name));
        }
    }


}

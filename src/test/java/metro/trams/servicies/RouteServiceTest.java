package metro.trams.servicies;

import metro.trams.constants.ConstantsForTests;
import metro.trams.entity.Route;
import metro.trams.exceptions.NoSuchRouteException;
import metro.trams.mappers.MapperDto;
import metro.trams.repositories.RoutesRepository;
import metro.trams.servicies.interfacies.RoutesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static metro.trams.constants.ConstantsForTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {
    @Mock
    RoutesRepository repository;
    @Mock
    MapperDto mapper;
    @InjectMocks
    RoutesServiceImpl service;

    @BeforeEach
    void setUp() {
        LIST_OF_ROUTES.add(ROUTE_2);
        LIST_OF_ROUTES.add(ROUTE_3);

        ROUTE_1.setId(1L);
        ROUTE_1.setCode("R111");
        ROUTE_1.setName("start-end");
    }

    @Test
    void getAllRoutes() {
        when(repository.findAll()).thenReturn(LIST_OF_ROUTES);

        List<Route> allRoutes = service.getAllRoutes();
        assertEquals(LIST_OF_ROUTES.size(), allRoutes.size());
        assertTrue(allRoutes.contains(ROUTE_2));

    }

    @Test
    void getRouteById_whenSuccess() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(ROUTE_1));

        ResponseEntity<Route> response = service.getRouteById(1L);

        assertEquals(ResponseEntity.ok(ROUTE_1), response);
    }

    @Test
    void getRouteById_whenNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        NoSuchRouteException exception = assertThrows(NoSuchRouteException.class, () -> service.getRouteById(1L));
        assertEquals("Mаршрут по id 1 не найден", exception.getMessage());
    }

    @Test
    void saveIntoDb() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateRoute() {
    }
}
package metro.trams.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import metro.trams.entity.Route;
import metro.trams.servicies.interfacies.RoutesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trams")
@RequiredArgsConstructor
public class RoutesController {
    private final RoutesService service;

    @Operation(summary = "получение всех маршрутов")
    @GetMapping
    public List<Route> getAllRoutes() {
        return service.getAllRoutes();
    }

    @Operation(summary = "получение маршрута по id", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "маршрут по id не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        return service.getRouteById(id);
    }

    @Operation(summary = "сохранение в бд маршрута", responses = {
            @ApiResponse(responseCode = "200", description = "вернет id сохраненного маршрута в БД"),
            @ApiResponse(responseCode = "400", description = "маршрут уже существует")
    })
    @PostMapping
    public ResponseEntity<Long> saveRouteIntoDb(@RequestParam String code,
                                                @Parameter(description = "название от конечной остановки – до конечной остановки") @RequestParam String name) {
        return service.saveIntoDb(code, name);
    }

    @Operation(summary = "удаление маршрута по id", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "маршрут по id не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRouteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Operation(summary = "обновление маршрута", responses = {
            @ApiResponse(responseCode = "200", description = "обновленный маршрут"),
            @ApiResponse(responseCode = "404", description = "маршрут по id не найден")
    })
    @PatchMapping("{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id,
                                             @RequestParam(required = false) String code,
                                             @Parameter(description = "название от конечной остановки – до конечной остановки") @RequestParam(required = false) String name
    ) {
        return service.updateRoute(id, code, name);
    }


}

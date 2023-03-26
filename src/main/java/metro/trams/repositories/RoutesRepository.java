package metro.trams.repositories;

import metro.trams.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface RoutesRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByCodeIgnoreCase(String code);

    Optional<Route> findByNameIgnoreCase(String name);
}

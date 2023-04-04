package metro.trams.components;

import metro.trams.repositories.RoutesRepository;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class TramsInfoContributor implements InfoContributor {
    private RoutesRepository repository;

    public TramsInfoContributor(RoutesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void contribute(Info.Builder builder) {
        long count = repository.count();
        builder.withDetail("routs-count", count);
    }
}

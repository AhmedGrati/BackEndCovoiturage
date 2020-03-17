package wassalni.covoiturage;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "covoiturage", path = "covoiturage")
public interface CovoiturageRepo extends PagingAndSortingRepository<Covoiturage, Long> {
}

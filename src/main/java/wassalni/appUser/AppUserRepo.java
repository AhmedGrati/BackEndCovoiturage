package wassalni.appUser;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Appuser", path = "appuser")
public interface AppUserRepo extends PagingAndSortingRepository<AppUser, Long> {
}

package jwlee.swm_refactoring.domain.repository;

import jwlee.swm_refactoring.domain.repository.entity.FacilityEntity;
import org.springframework.data.repository.CrudRepository;

public interface FacilityRepository extends CrudRepository<FacilityEntity, Integer> {
}

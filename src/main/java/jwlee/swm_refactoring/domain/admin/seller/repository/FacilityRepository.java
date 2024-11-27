package jwlee.swm_refactoring.domain.admin.seller.repository;

import jwlee.swm_refactoring.domain.admin.seller.repository.entity.FacilityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FacilityRepository extends CrudRepository<FacilityEntity, Integer> {
    List<FacilityEntity> findAllByAccommodationId(Long accommodationId);

}

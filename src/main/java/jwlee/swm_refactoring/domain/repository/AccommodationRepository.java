package jwlee.swm_refactoring.domain.repository;

import jwlee.swm_refactoring.domain.model.Accommodation;
import jwlee.swm_refactoring.domain.repository.entity.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Integer> {
}

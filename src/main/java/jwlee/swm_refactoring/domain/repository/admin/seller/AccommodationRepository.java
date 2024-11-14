package jwlee.swm_refactoring.domain.repository.admin.seller;

import jwlee.swm_refactoring.domain.repository.admin.seller.entity.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Integer> {
}

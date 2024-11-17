package jwlee.swm_refactoring.domain.admin.seller.repository;

import jwlee.swm_refactoring.domain.admin.seller.repository.entity.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Integer> {
}
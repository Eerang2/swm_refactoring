package jwlee.swm_refactoring.domain.admin.seller.repository;

import jwlee.swm_refactoring.domain.admin.seller.repository.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {


}

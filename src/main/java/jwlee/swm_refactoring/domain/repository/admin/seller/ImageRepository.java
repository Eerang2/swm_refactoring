package jwlee.swm_refactoring.domain.repository.admin.seller;

import jwlee.swm_refactoring.domain.repository.admin.seller.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {


}

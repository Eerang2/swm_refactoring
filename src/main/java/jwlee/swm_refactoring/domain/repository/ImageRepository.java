package jwlee.swm_refactoring.domain.repository;

import jwlee.swm_refactoring.domain.repository.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {


}

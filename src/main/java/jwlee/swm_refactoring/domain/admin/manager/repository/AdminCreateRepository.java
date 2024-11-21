package jwlee.swm_refactoring.domain.admin.manager.repository;

import jwlee.swm_refactoring.domain.admin.manager.repository.entity.AdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminCreateRepository extends CrudRepository<AdminEntity, Integer> {

    Optional<AdminEntity> findByAdminId(String adminId);

}

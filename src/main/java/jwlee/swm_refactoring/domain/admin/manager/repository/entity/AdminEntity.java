package jwlee.swm_refactoring.domain.admin.manager.repository.entity;

import jakarta.persistence.*;
import jwlee.swm_refactoring.domain.admin.manager.enums.AdminRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "seller_admin")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String adminId;

    private String password;

    @Enumerated(EnumType.STRING)
    private AdminRole adminRole;


}

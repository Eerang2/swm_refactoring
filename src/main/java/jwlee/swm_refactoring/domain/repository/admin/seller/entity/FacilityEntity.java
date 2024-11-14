package jwlee.swm_refactoring.domain.repository.admin.seller.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facility")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_no", nullable = false)
    private Long id;

    @Column
    private String name;

    @JoinColumn(name = "accommodation_no")
    private Long accommodationId;

}

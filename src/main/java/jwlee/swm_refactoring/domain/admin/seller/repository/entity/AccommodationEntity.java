package jwlee.swm_refactoring.domain.admin.seller.repository.entity;

import jakarta.persistence.*;
import jwlee.swm_refactoring.domain.admin.seller.enums.AccommodationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "acmd")
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_no", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private String address1;

    @Column(nullable = false)
    private String address2;
}

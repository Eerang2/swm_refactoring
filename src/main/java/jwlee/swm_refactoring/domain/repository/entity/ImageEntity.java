package jwlee.swm_refactoring.domain.repository.entity;

import jakarta.persistence.*;
import jwlee.swm_refactoring.domain.enums.ImageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "accommodation_image")
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_no", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImageType imageType;

    @Column(nullable = false)
    private String path;

    @Column(name = "accommodation_no", nullable = false)
    private Long accommodationId;

}

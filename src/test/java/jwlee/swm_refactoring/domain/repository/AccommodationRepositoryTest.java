package jwlee.swm_refactoring.domain.repository;

import jwlee.swm_refactoring.domain.enums.admin.seller.AccommodationType;
import jwlee.swm_refactoring.domain.model.admin.seller.Accommodation;
import jwlee.swm_refactoring.domain.model.admin.seller.GeoLocation;
import jwlee.swm_refactoring.domain.repository.admin.seller.AccommodationRepository;
import jwlee.swm_refactoring.domain.repository.admin.seller.entity.AccommodationEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    @DisplayName("숙소 등록 성공")
    public void saveAccommodation() {
        Accommodation accommodation = Accommodation.builder()
                .name("신라호텔")
                .phone("010-1111-2222")
                .description("5성급 최고급 호텔")
                .address1("경수대로 947")
                .address2("안양시 동안구 비산동")
                .geoLocation(new GeoLocation(12.122222, 142.12323))
                .accommodationType(AccommodationType.HOTEL)
                .build();
        AccommodationEntity savedAccommodation = accommodationRepository.save(accommodation.toEntity());

        assertThat(savedAccommodation.getId()).isNotNull();
        assertThat(savedAccommodation.getId()).isEqualTo(1L);
    }
}

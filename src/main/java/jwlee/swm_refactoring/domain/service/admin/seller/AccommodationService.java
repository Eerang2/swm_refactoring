package jwlee.swm_refactoring.domain.service.admin.seller;

import jwlee.swm_refactoring.domain.model.admin.seller.Accommodation;
import jwlee.swm_refactoring.domain.model.admin.seller.Facility;
import jwlee.swm_refactoring.domain.repository.admin.seller.AccommodationRepository;
import jwlee.swm_refactoring.domain.repository.admin.seller.FacilityRepository;
import jwlee.swm_refactoring.domain.repository.admin.seller.entity.AccommodationEntity;
import jwlee.swm_refactoring.domain.repository.admin.seller.entity.FacilityEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final FacilityRepository facilityRepository;

    @Transactional
    public Accommodation saveAccommodation(Accommodation accommodation) {
        AccommodationEntity accommodationEntity =  accommodationRepository.save(accommodation.toEntity());
        return Accommodation.from(accommodationEntity);
    }

    @Transactional
    public void saveFacilities(List<Facility> facilities) {
        List<FacilityEntity> entities = facilities.stream()
                                        .map(Facility::toEntity)
                                        .collect(Collectors.toList());
        facilityRepository.saveAll(entities);
    }
}

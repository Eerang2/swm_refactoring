package jwlee.swm_refactoring.domain.admin.seller.service;

import jwlee.swm_refactoring.domain.admin.seller.model.Accommodation;
import jwlee.swm_refactoring.domain.admin.seller.model.Facility;
import jwlee.swm_refactoring.domain.admin.seller.repository.FacilityRepository;
import jwlee.swm_refactoring.domain.admin.seller.repository.AccommodationRepository;
import jwlee.swm_refactoring.domain.admin.seller.repository.entity.AccommodationEntity;
import jwlee.swm_refactoring.domain.admin.seller.repository.entity.FacilityEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public Accommodation findSellerById(long id) {
        AccommodationEntity accommodation =  accommodationRepository.findSellerById(id).orElseThrow(RuntimeException::new);
        return Accommodation.from(accommodation);
    }

    public List<String> findFacilitiesById(long id) {
        return facilityRepository.findAllByAccommodationId(id).stream()
                .map(FacilityEntity::getName)
                .toList();
    }
}

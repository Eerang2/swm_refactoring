package jwlee.swm_refactoring.domain.service;

import jwlee.swm_refactoring.domain.model.Accommodation;
import jwlee.swm_refactoring.domain.model.Facility;
import jwlee.swm_refactoring.domain.repository.AccommodationRepository;
import jwlee.swm_refactoring.domain.repository.FacilityRepository;
import jwlee.swm_refactoring.domain.repository.entity.AccommodationEntity;
import jwlee.swm_refactoring.domain.repository.entity.FacilityEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        facilities.stream()
                .map(Facility::toEntity)
                .forEach(facilityRepository::save);
    }
}

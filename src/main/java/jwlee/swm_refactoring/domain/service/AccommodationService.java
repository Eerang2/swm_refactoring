package jwlee.swm_refactoring.domain.service;

import jwlee.swm_refactoring.domain.model.Accommodation;
import jwlee.swm_refactoring.domain.repository.AccommodationRepository;
import jwlee.swm_refactoring.domain.repository.entity.AccommodationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccommodationService {

    @Autowired
    private final AccommodationRepository accommodationRepository;

    @Transactional
    public Accommodation save(Accommodation accommodation) {
        AccommodationEntity accommodationEntity =  accommodationRepository.save(accommodation.toEntity());
        return Accommodation.from(accommodationEntity);
    }
}

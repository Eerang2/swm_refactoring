package jwlee.swm_refactoring.domain.service;


import jwlee.swm_refactoring.domain.enums.ImageType;
import jwlee.swm_refactoring.domain.repository.ImageRepository;
import jwlee.swm_refactoring.domain.repository.entity.ImageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {

    private final ImageRepository imageRepository;

    public ImageEntity uploadAccommodationImage(ImageType imageType, MultipartFile imageFile) throws IOException {
        final String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
        final String fileDirectory = "tmpFile/accommodation" + imageType.toString();
        final String filePath = fileDirectory + "/" + fileName;


        File destDirectory = new File(fileDirectory);
        if (!destDirectory.exists()) {
            destDirectory.mkdirs();
        }

        File destFile = new File(filePath);
        imageFile.transferTo(destFile);

        ImageEntity imageEntity = ImageEntity.builder()
                .imageType(imageType)
                .path(filePath)
                .build();

        return imageRepository.save(imageEntity);
    }
}

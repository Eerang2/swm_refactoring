package jwlee.swm_refactoring.web.accommodation;
import com.fasterxml.jackson.databind.ObjectMapper;
import jwlee.swm_refactoring.domain.enums.AccommodationType;
import jwlee.swm_refactoring.domain.enums.ImageType;
import jwlee.swm_refactoring.domain.model.Accommodation;
import jwlee.swm_refactoring.domain.model.GeoLocation;
import jwlee.swm_refactoring.domain.service.AccommodationService;
import jwlee.swm_refactoring.web.dto.AccommodationReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class AccommodationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccommodationService accommodationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("숙소 등록 성공")
    void save() throws Exception {
        String requestBody = objectMapper.writeValueAsString(
                AccommodationReq.builder()
                        .name("신라호텔")
                        .phone("010-1111-2222")
                        .address1("경수대로 947")
                        .address2("안양시 동안구")
                        .description("5성급 최고급 호텔")
                        .latitude(12.122222)
                        .longitude(142.56634)
                        .accommodationType(AccommodationType.HOTEL)
                        .build()
        );

        // when
        final ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/accommodation/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );
        resultActions.andExpect(status().isOk());
    }
}
package org.rymarski.motionprocessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.rymarski.motionprocessor.api.dto.MotionCreateRequest;
import org.rymarski.motionprocessor.api.dto.MotionSearchRequest;
import org.rymarski.motionprocessor.api.dto.MotionUpdateRequest;
import org.rymarski.motionprocessor.motion.Motion;
import org.rymarski.motionprocessor.motion.MotionService;
import org.rymarski.motionprocessor.motion.UnableToChangeMotionStatusException;
import org.rymarski.motionprocessor.motion.UnableToEditMotion;
import org.rymarski.motionprocessor.pageable.PageableSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(classes = MotionProcessorApplication.class)
@Sql(scripts = {"classpath:test_db_setup.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Transactional
class MotionServiceTest {

    @Autowired
    private MotionService motionService;

    @Test
    @Order(1)
    void shouldReturnNewMotionIdOnSuccessfulCreate() {
        MotionCreateRequest motionCreateRequest = new MotionCreateRequest("testMotion", "testContent");
        Long resultId = motionService.create(motionCreateRequest);

        assertNotNull(resultId);
    }

    @Test
    @Order(2)
    void shouldReturnOnlyNewestVersionForSearchRequest() {
        MotionSearchRequest motionSearchRequest = MotionSearchRequest.builder()
                .page(1)
                .size(5)
                .name("motion7")
                .build();
        PageableSearchResponse<Motion> response = motionService.searchPage(motionSearchRequest);

        assertFalse(response.getPageContent().isEmpty());
        assertEquals(1, response.getAllElementsCount());
    }

    @Test
    @Order(3)
    void shouldGetAllVersionsForGetRequest() {
        List<Motion> response = motionService.get(777L);

        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    @Order(4)
    void shouldUpdateInCreatedState() {
        MotionUpdateRequest update1 = new MotionUpdateRequest(111L, "update1");

        assertDoesNotThrow(() -> motionService.update(update1));
    }

    @Test
    @Order(5)
    void shouldUpdateInVerifiedState() {
        MotionUpdateRequest update2 = new MotionUpdateRequest(222L, "update2");

        assertDoesNotThrow(() -> motionService.update(update2));
    }

    @Test
    @Order(6)
    void shouldNotUpdateInAcceptedState() {
        MotionUpdateRequest update3 = new MotionUpdateRequest(333L, "update3");

        assertThrowsExactly(UnableToEditMotion.class, () -> motionService.update(update3));
    }

    @Test
    @Order(7)
    void shouldNotUpdateInPublishedState() {
        MotionUpdateRequest update4 = new MotionUpdateRequest(444L, "update4");

        assertThrowsExactly(UnableToEditMotion.class, () -> motionService.update(update4));
    }

    @Test
    @Order(8)
    void shouldNotUpdateInDeletedState() {
        MotionUpdateRequest update5 = new MotionUpdateRequest(555L, "update5");

        assertThrowsExactly(UnableToEditMotion.class, () -> motionService.update(update5));
    }

    @Test
    @Order(9)
    void shouldNotUpdateInRejectedState() {
        MotionUpdateRequest update6 = new MotionUpdateRequest(666L, "update6");

        assertThrowsExactly(UnableToEditMotion.class, () -> motionService.update(update6));
    }

    @Test
    @Order(10)
    void shouldProgressInCreatedState() {
        assertDoesNotThrow(() -> motionService.progress(111L));
    }

    @Test
    @Order(11)
    void shouldProgressInVerifiedState() {
        assertDoesNotThrow(() -> motionService.progress(222L));
    }

    @Test
    @Order(12)
    void shouldProgressInAcceptedState() {
        assertDoesNotThrow(() -> motionService.progress(333L));
    }

    @Test
    @Order(13)
    void shouldNotProgressInPublishedState() {
        assertThrowsExactly(UnableToChangeMotionStatusException.class, () -> motionService.progress(444L));
    }

    @Test
    @Order(14)
    void shouldNotProgressInDeletedState() {
        assertThrowsExactly(UnableToChangeMotionStatusException.class, () -> motionService.progress(555L));
    }

    @Test
    @Order(15)
    void shouldNotProgressInRejectedState() {
        assertThrowsExactly(UnableToChangeMotionStatusException.class, () -> motionService.progress(666L));
    }

    @Test
    @Order(16)
    void shouldDenyInCreatedState() {
        assertDoesNotThrow(() -> motionService.deny(111L));
    }

    @Test
    @Order(11)
    void shouldDenyInVerifiedState() {
        assertDoesNotThrow(() -> motionService.deny(222L));
    }

    @Test
    @Order(12)
    void shouldDenyInAcceptedState() {
        assertDoesNotThrow(() -> motionService.deny(333L));
    }

    @Test
    @Order(13)
    void shouldNotDenyInPublishedState() {
        assertThrowsExactly(UnableToChangeMotionStatusException.class, () -> motionService.deny(444L));
    }

    @Test
    @Order(14)
    void shouldNotDenyInDeletedState() {
        assertThrowsExactly(UnableToChangeMotionStatusException.class, () -> motionService.deny(555L));
    }

    @Test
    @Order(15)
    void shouldNotDenyInRejectedState() {
        assertThrowsExactly(UnableToChangeMotionStatusException.class, () -> motionService.deny(666L));
    }
}
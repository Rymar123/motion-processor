package org.rymarski.motionprocessor.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rymarski.motionprocessor.api.dto.MotionCreateRequest;
import org.rymarski.motionprocessor.api.dto.MotionSearchRequest;
import org.rymarski.motionprocessor.api.dto.MotionUpdateRequest;
import org.rymarski.motionprocessor.motion.Motion;
import org.rymarski.motionprocessor.motion.MotionCoreService;
import org.rymarski.motionprocessor.pageable.PageableSearchResponse;
import org.rymarski.motionprocessor.security.AuthConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/motion")
@Slf4j
@RequiredArgsConstructor
public class MotionController {
  private final MotionCoreService motionService;

  @GetMapping
  public PageableSearchResponse<Motion> searchPage(MotionSearchRequest searchRequest) {
    log.debug("Received search motion request: {}", searchRequest);
    return motionService.searchPage(searchRequest);
  }

  @GetMapping("/id")
  public List<Motion> search(@RequestParam Long id) {
    log.debug("Received search motion request for id: {}", id);
    return motionService.search(id);
  }

  @PostMapping
  @PreAuthorize(AuthConstants.CAN_ADD_MOTION)
  public long create(@RequestBody MotionCreateRequest motionCreateRequest) {
    log.debug("Received motion create request: {}", motionCreateRequest);
    Long result = motionService.create(motionCreateRequest);
    return result != null ? result : 0L;
  }

  @PatchMapping
  @PreAuthorize(AuthConstants.CAN_UPDATE_MOTION)
  public void update(@RequestBody MotionUpdateRequest motionUpdateRequest) {
    log.debug("Received motion update request: {}", motionUpdateRequest);
    motionService.update(motionUpdateRequest);
  }

  @PatchMapping("/progress")
  @PreAuthorize(AuthConstants.CAN_PROGRESS_MOTION)
  public void progress(@RequestParam Long id) {
    log.debug("Received motion progress request for id {}", id);
    motionService.progress(id);
  }

  @PatchMapping("/deny")
  @PreAuthorize(AuthConstants.CAN_DENY_MOTION)
  public void deny(@RequestParam Long id) {
    log.debug("Received motion deny request for id {}", id);
    motionService.deny(id);
  }
}
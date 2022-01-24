package org.rymarski.motionprocessor.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rymarski.motionprocessor.api.dto.MotionCreateRequest;
import org.rymarski.motionprocessor.api.dto.MotionSearchRequest;
import org.rymarski.motionprocessor.api.dto.MotionUpdateRequest;
import org.rymarski.motionprocessor.motion.Motion;
import org.rymarski.motionprocessor.motion.MotionCoreService;
import org.rymarski.motionprocessor.pageable.PageableSearchResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/motion")
@Slf4j
@RequiredArgsConstructor
public class MotionController {
  private final MotionCoreService motionService;

  @GetMapping("/search")
  public PageableSearchResponse<Motion> searchPage(MotionSearchRequest searchRequest) {
    log.debug("Received search motion request: {}", searchRequest);
    return motionService.searchPage(searchRequest);
  }

  @GetMapping
  public List<Motion> get(@RequestParam Long id) {
    log.debug("Received search motion request for id: {}", id);
    return motionService.search(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('MANAGER')")
  public long create(@RequestBody MotionCreateRequest motionCreateRequest) {
    log.debug("Received motion create request: {}", motionCreateRequest);
    Long result = motionService.create(motionCreateRequest);
    return result != null ? result : 0L;
  }

  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('MANAGER')")
  public void update(@RequestBody MotionUpdateRequest motionUpdateRequest) {
    log.debug("Received motion update request: {}", motionUpdateRequest);
    motionService.update(motionUpdateRequest);
  }

  @PostMapping(value = "/progress")
  @PreAuthorize("hasRole('MANAGER')")
  public void progress(@RequestParam Long id) {
    log.debug("Received motion progress request for id {}", id);
    motionService.progress(id);
  }

  @PostMapping("/deny")
  @PreAuthorize("hasRole('MANAGER')")
  public void deny(@RequestParam Long id) {
    log.debug("Received motion deny request for id {}", id);
    motionService.deny(id);
  }
}

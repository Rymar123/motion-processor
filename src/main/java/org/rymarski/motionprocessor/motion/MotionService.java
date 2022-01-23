package org.rymarski.motionprocessor.motion;

import org.rymarski.motionprocessor.api.dto.MotionCreateRequest;
import org.rymarski.motionprocessor.api.dto.MotionSearchRequest;
import org.rymarski.motionprocessor.api.dto.MotionUpdateRequest;
import org.rymarski.motionprocessor.pageable.PageableSearchResponse;

import java.util.List;

public interface MotionService {
  PageableSearchResponse<Motion> searchPage(MotionSearchRequest searchRequest);

  List<Motion> search(Long id);

  Long create(MotionCreateRequest motionCreateRequest);

  void update(MotionUpdateRequest motionUpdateRequest);

  void progress(long id);

  void deny(long id);
}

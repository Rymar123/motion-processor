package org.rymarski.motionprocessor.pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageRequestProvider {
  public PageRequest getPageRequest(PageableSearchRequest request) {
    String sortedBy = request.getSortedBy();

    if (sortedBy == null || sortedBy.isEmpty()) {
      return PageRequest.of(
              request.getPage() - 1,
              request.getSize()
      );
    }

    return PageRequest.of(
            request.getPage() - 1,
            request.getSize(),
            Sort.by(request.getSortDirection(), sortedBy)
    );
  }
}

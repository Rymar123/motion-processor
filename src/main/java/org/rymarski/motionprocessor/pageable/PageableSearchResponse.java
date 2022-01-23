package org.rymarski.motionprocessor.pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageableSearchResponse<T> implements Serializable {
  private static final long serialVersionUID = -6110134077319388358L;

  private int page;
  private int size;
  private int allPagesCount;
  private long allElementsCount;
  private List<T> pageContent;
}

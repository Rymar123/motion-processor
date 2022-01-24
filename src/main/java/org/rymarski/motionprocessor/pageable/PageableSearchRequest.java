package org.rymarski.motionprocessor.pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageableSearchRequest {
    public static final String DEFAULT_SORT_BY_COLUMN = "id";

    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;
    @Builder.Default
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    @Builder.Default
    private String sortedBy = DEFAULT_SORT_BY_COLUMN;
}

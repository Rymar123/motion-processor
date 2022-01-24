package org.rymarski.motionprocessor.motion;

import lombok.RequiredArgsConstructor;
import org.rymarski.motionprocessor.api.dto.MotionCreateRequest;
import org.rymarski.motionprocessor.api.dto.MotionSearchRequest;
import org.rymarski.motionprocessor.api.dto.MotionUpdateRequest;
import org.rymarski.motionprocessor.pageable.PageRequestProvider;
import org.rymarski.motionprocessor.pageable.PageableSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MotionCoreService implements MotionService {
    private final MotionRepository repository;
    private final MotionConverter converter;
    private final PageRequestProvider pageRequestProvider;
    private final MotionPromoter promoter;
    private final MotionDenier denier;

    public PageableSearchResponse<Motion> searchPage(MotionSearchRequest searchRequest) {
        Page<MotionEntity> page = repository.findAllByNameAndStatus(
                searchRequest.getName(),
                searchRequest.getStatus(),
                pageRequestProvider.getPageRequest(searchRequest));

        return PageableSearchResponse.<Motion>builder()
                .page(searchRequest.getPage())
                .size(searchRequest.getSize())
                .allPagesCount(page.getTotalPages())
                .allElementsCount(page.getTotalElements())
                .pageContent(page.stream().map(converter::toDto).collect(Collectors.toList()))
                .build();
    }

    public List<Motion> get(Long id) {
        return repository.findAllByCompositeIdIdOrderByCompositeIdVersionDesc(id)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    public Long create(MotionCreateRequest motionCreateRequest) {
        Motion motion = Motion.builder()
                .name(motionCreateRequest.getName())
                .status(Motion.Status.CREATED)
                .build();
        MotionEntity entity = converter.toEntity(motion);

        return repository.save(entity).getId();
    }

    public void update(MotionUpdateRequest motionUpdateRequest) {
        MotionEntity entity = repository.findTop1ByCompositeIdIdOrderByCompositeIdVersionDesc(motionUpdateRequest.getId());
        String status = entity.getStatus();

        if (Motion.Status.CREATED.name().equals(status) || Motion.Status.VERIFIED.name().equals(status)) {
            entity.setContent(motionUpdateRequest.getEditedContent());
            //entity.incrementVersion();
            repository.save(entity);
        } else {
            throw new UnableToEditMotion(motionUpdateRequest.getId());
        }
    }

    public void progress(long id) {
        MotionEntity entity = repository.findTop1ByCompositeIdIdOrderByCompositeIdVersionDesc(id);
        promoter.run(entity);
        //entity.incrementVersion();
        repository.save(entity);
    }

    public void deny(long id) {
        MotionEntity entity = repository.findTop1ByCompositeIdIdOrderByCompositeIdVersionDesc(id);
        denier.run(entity);
        //entity.incrementVersion();
        repository.save(entity);
    }
}

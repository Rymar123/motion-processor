package org.rymarski.motionprocessor.motion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface MotionRepository extends JpaRepository<MotionEntity, Long>, JpaSpecificationExecutor<MotionEntity> {

    @Query(value = "select m from MotionEntity m " +
            "where (:status is null or m.status =:status) " +
            "and (:name is null or m.name like %:name% )" +
            "and m.compositeId.version = " +
            "(SELECT max(m2.compositeId.version) FROM MotionEntity m2 " +
            "WHERE m.compositeId.id = m2.compositeId.id)")
    Page<MotionEntity> findAllByNameAndStatus(@Param("name") String name, @Param("status") String status, Pageable pageable);

    List<MotionEntity> findAllByCompositeIdIdOrderByCompositeIdVersionDesc(@Param("compositeIdId") Long id);

    MotionEntity findTop1ByCompositeIdIdOrderByCompositeIdVersionDesc(@Param("compositeIdId") Long id);
}

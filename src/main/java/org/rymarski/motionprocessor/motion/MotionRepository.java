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
  // language=SQL
  String SELECT_MOTIONS_BY_NAME_AND_STATUS_AND_MAX_VERSION = "select * from motions " +
          "where status =:status " +
          "and name like '%:name%' " +
          "group by id " +
          "having version = MAX(version)";

  // language=SQL
  String COUNT_MOTION_BY_NAME_AND_STATUS_AND_MAX_VERSION = "select count(*) from motions " +
          "where status =:status " +
          "and name like '%:name%' " +
          "group by id " +
          "having version = MAX(version)";

  @Query(value = SELECT_MOTIONS_BY_NAME_AND_STATUS_AND_MAX_VERSION,
          countQuery = COUNT_MOTION_BY_NAME_AND_STATUS_AND_MAX_VERSION,
          nativeQuery = true)
  Page<MotionEntity> findAllByNameAndStatus(@Param("name") String name, @Param("status") String status, Pageable pageable);

  List<MotionEntity> findAllByIdOrderByVersionDesc(Long id);
}

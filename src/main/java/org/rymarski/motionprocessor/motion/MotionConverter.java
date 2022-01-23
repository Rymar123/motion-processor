package org.rymarski.motionprocessor.motion;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rymarski.motionprocessor.converter.EntityConverter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MotionConverter implements EntityConverter<MotionEntity, Motion> {
  private final ModelMapper modelMapper;

  @Override
  public MotionEntity toEntity(Motion motion) {
    return modelMapper.map(motion, MotionEntity.class);
  }

  @Override
  public Motion toDto(MotionEntity motionEntity) {
    return modelMapper.map(motionEntity, Motion.class);
  }
}

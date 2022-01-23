package org.rymarski.motionprocessor.motion;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MotionConverterConfiguration {

  @Bean
  public ModelMapper entityConverterMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper.createTypeMap(MotionEntity.class, Motion.class);
    mapper.getConfiguration().setSkipNullEnabled(true);
    mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    return mapper;
  }
}

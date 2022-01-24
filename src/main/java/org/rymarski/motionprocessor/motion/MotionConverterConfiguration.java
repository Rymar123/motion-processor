package org.rymarski.motionprocessor.motion;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MotionConverterConfiguration {

    @Bean
    public ModelMapper entityConverterMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Motion, MotionEntity> fromDto = mapper.createTypeMap(Motion.class, MotionEntity.class);
        fromDto.addMappings(
                mapping -> {
                    mapping.map(Motion::getId, MotionEntity::setId);
                    mapping.map(Motion::getVersion, MotionEntity::setVersion);
                }
        );

        TypeMap<MotionEntity, Motion> fromEntity = mapper.createTypeMap(MotionEntity.class, Motion.class);
        fromEntity.addMappings(
                mapping -> {
                    mapping.map(MotionEntity::getId, Motion::setId);
                    mapping.map(MotionEntity::getVersion, Motion::setVersion);
                }
        );

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }
}

package com.drinkwater.apidrinkwater.hydrationtracking.mapper;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeCreateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WaterIntakeMapper {

    WaterIntakeMapper INSTANCE = Mappers.getMapper(WaterIntakeMapper.class);

    void toEntity(WaterIntakeCreateDTO dto, @MappingTarget WaterIntake waterIntake);

    @Mapping(target = "userId", source = "user.id")
    WaterIntakeResponseDTO toDto(WaterIntake waterIntake);
}

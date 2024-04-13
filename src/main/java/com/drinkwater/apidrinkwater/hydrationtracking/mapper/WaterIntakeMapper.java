package com.drinkwater.apidrinkwater.hydrationtracking.mapper;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeCreateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WaterIntakeMapper {

    WaterIntakeMapper INSTANCE = Mappers.getMapper(WaterIntakeMapper.class);

    WaterIntake toCreateDto(WaterIntakeCreateDTO createDTO);
}

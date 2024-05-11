package com.drinkwater.apidrinkwater.hydrationtracking.mapper;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AlarmSettingsMapper {

    AlarmSettingsMapper INSTANCE = Mappers.getMapper(AlarmSettingsMapper.class);

    AlarmSettings toEntity(AlarmSettingsDTO dto);
}

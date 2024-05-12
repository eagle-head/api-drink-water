package com.drinkwater.apidrinkwater.hydrationtracking.mapper;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsCreateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsResponseDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.dto.AlarmSettingsUpdateDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.AlarmSettings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AlarmSettingsMapper {

    AlarmSettingsMapper INSTANCE = Mappers.getMapper(AlarmSettingsMapper.class);

    AlarmSettings toEntity(AlarmSettingsCreateDTO dto);

    AlarmSettings toEntity(AlarmSettingsUpdateDTO dto);

    AlarmSettingsResponseDTO toDto(AlarmSettings entity);
}

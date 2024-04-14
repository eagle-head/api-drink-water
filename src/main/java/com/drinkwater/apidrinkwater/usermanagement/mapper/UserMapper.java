package com.drinkwater.apidrinkwater.usermanagement.mapper;

import com.drinkwater.apidrinkwater.hydrationtracking.mapper.AlarmSettingsMapper;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserCreateDTO;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserResponseDTO;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserUpdateDTO;
import com.drinkwater.apidrinkwater.usermanagement.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = AlarmSettingsMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(UserUpdateDTO dto, @MappingTarget User entity);

    UserResponseDTO toDto(User user);
}


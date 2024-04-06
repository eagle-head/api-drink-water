package com.drinkwater.apidrinkwater.usermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.drinkwater.apidrinkwater.usermanagement.dto.UserDTO;
import com.drinkwater.apidrinkwater.usermanagement.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDTO(User user);
}

package com.leeviding.leevinote.user.infrastructure.mapper;

import com.leeviding.leevinote.user.domain.model.User;
import com.leeviding.leevinote.user.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User user);
}
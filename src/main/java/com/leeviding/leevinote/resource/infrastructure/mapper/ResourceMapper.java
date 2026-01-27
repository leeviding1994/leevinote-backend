package com.leeviding.leevinote.resource.infrastructure.mapper;

import com.leeviding.leevinote.resource.domain.model.Resource;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    @Mapping(target = "children", ignore = true)
    Resource toDomain(ResourceEntity entity);

    ResourceEntity toEntity(Resource resource);

}

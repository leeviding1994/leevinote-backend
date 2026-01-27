package com.leeviding.leevinote.resource.infrastructure.mapper;

import com.leeviding.leevinote.resource.domain.model.MarkMenu;
import com.leeviding.leevinote.resource.domain.model.Resource;
import com.leeviding.leevinote.resource.infrastructure.entity.MarkMenuEntity;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceEntity;
import com.leeviding.leevinote.resource.web.response.ResourceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MarkMenuMapper {

    @Mapping(target = "resource",  qualifiedByName = "mapResource")
    MarkMenu toDomain(MarkMenuEntity entity);

    @Mapping(target = "resource",  qualifiedByName = "resourceDomain2Entity")
    MarkMenuEntity toEntity(MarkMenu menu);

    @Named("mapResource")
    default Resource mapResource(ResourceEntity resourceEntity) {
        return resourceEntity == null ? null : Resource.build(resourceEntity);
    }

    @Named("resourceDomain2Entity")
    default ResourceEntity resourceDomain2Entity(Resource resource) {
        return resource == null ? null : ResourceEntity.build(resource);
    }
}

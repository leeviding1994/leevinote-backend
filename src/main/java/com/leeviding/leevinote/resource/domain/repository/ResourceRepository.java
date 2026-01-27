package com.leeviding.leevinote.resource.domain.repository;

import com.leeviding.leevinote.resource.infrastructure.entity.ResourceEntity;

import java.util.List;

public interface ResourceRepository {

    List<ResourceEntity> findAll();
}

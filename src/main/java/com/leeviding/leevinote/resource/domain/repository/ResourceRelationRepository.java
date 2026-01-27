package com.leeviding.leevinote.resource.domain.repository;

import com.leeviding.leevinote.resource.domain.model.ResourceRelation;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceRelationEntity;

import java.util.List;

public interface ResourceRelationRepository {
    List<ResourceRelationEntity> findByAncestorId(Long id);
    List<ResourceRelationEntity> findByDepth(int depth);
}

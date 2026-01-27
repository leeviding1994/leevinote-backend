package com.leeviding.leevinote.resource.infrastructure.repository;

import com.leeviding.leevinote.resource.infrastructure.entity.ResourceRelationEntity;
import com.leeviding.leevinote.resource.infrastructure.id.ResourceRelationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaResourceRelationRepository extends JpaRepository<ResourceRelationEntity, ResourceRelationId> {

    List<ResourceRelationEntity> findByAncestorId(Long Id);

    List<ResourceRelationEntity> findByDepth(int depth);
}

package com.leeviding.leevinote.resource.infrastructure.repository;

import com.leeviding.leevinote.resource.domain.model.ResourceRelation;
import com.leeviding.leevinote.resource.domain.repository.ResourceRelationRepository;
import com.leeviding.leevinote.resource.domain.repository.ResourceRepository;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceEntity;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceRelationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ResourceRelationRepositoryImpl implements ResourceRelationRepository {

    private final JpaResourceRelationRepository jpaResourceRelationRepository;

    @Override
    public List<ResourceRelationEntity> findByAncestorId(Long id) {
        return jpaResourceRelationRepository.findByAncestorId(id);
    }

    @Override
    public List<ResourceRelationEntity> findByDepth(int depth) {
        return jpaResourceRelationRepository.findByDepth(depth);
    }
}

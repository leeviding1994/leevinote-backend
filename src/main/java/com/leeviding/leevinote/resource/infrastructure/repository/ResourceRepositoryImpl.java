package com.leeviding.leevinote.resource.infrastructure.repository;

import com.leeviding.leevinote.resource.domain.model.Resource;
import com.leeviding.leevinote.resource.domain.repository.ResourceRepository;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ResourceRepositoryImpl implements ResourceRepository {

    private final JpaResourceRepository jpaResourceRepository;

    @Override
    public List<ResourceEntity> findAll() {
        return jpaResourceRepository.findAll();
    }
}

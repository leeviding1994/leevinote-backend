package com.leeviding.leevinote.resource.infrastructure.repository;

import com.leeviding.leevinote.resource.domain.repository.ResourceRepository;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaResourceRepository extends JpaRepository<ResourceEntity, Long> {

}

package com.leeviding.leevinote.resource.infrastructure.repository;

import com.leeviding.leevinote.resource.infrastructure.entity.MarkMenuEntity;
import com.leeviding.leevinote.resource.infrastructure.id.MarkMenuId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaMarkMenuRepository extends JpaRepository<MarkMenuEntity, MarkMenuId> {

    List<MarkMenuEntity> findByUserId(Long userId);
}

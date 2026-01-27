package com.leeviding.leevinote.resource.infrastructure.repository;

import com.leeviding.leevinote.resource.domain.model.MarkMenu;
import com.leeviding.leevinote.resource.domain.repository.MarkMenuRepository;
import com.leeviding.leevinote.resource.domain.repository.ResourceRepository;
import com.leeviding.leevinote.resource.infrastructure.entity.MarkMenuEntity;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceEntity;
import com.leeviding.leevinote.resource.infrastructure.mapper.MarkMenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MarkMenuRepositoryImpl implements MarkMenuRepository {

    private final JpaMarkMenuRepository jpaMarkMenuRepository;
    private final MarkMenuMapper markMenuMapper;

    @Override
    public List<MarkMenu> getByUserId(Long userId) {
        List<MarkMenuEntity> markMenus = jpaMarkMenuRepository.findByUserId(userId);
        return markMenus.stream().map(markMenuMapper::toDomain).toList();
    }
}

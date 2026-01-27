package com.leeviding.leevinote.resource.application.service;

import com.leeviding.leevinote.resource.application.ResourceUseCase;
import com.leeviding.leevinote.resource.domain.model.MarkMenu;
import com.leeviding.leevinote.resource.domain.model.Resource;
import com.leeviding.leevinote.resource.domain.model.ResourceRelation;
import com.leeviding.leevinote.resource.domain.repository.MarkMenuRepository;
import com.leeviding.leevinote.resource.domain.repository.ResourceRelationRepository;
import com.leeviding.leevinote.resource.domain.repository.ResourceRepository;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceEntity;
import com.leeviding.leevinote.resource.infrastructure.entity.ResourceRelationEntity;
import com.leeviding.leevinote.resource.infrastructure.mapper.ResourceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResourceService implements ResourceUseCase {

    private final ResourceRepository resourceRepository;
    private final ResourceRelationRepository resourceRelationRepository;
    private final ResourceMapper resourceMapper;
    private final MarkMenuRepository markMenuRepository;

    @Override
    public List<Resource> getResourceTree(Long id) {
        List<ResourceRelationEntity> resourceRelationEntities = resourceRelationRepository.findByDepth(1);
        // 从数据库查询资源树
        List<ResourceEntity> resourceEntities = resourceRepository.findAll();
        return Resource.buildTree(resourceEntities.stream().map(Resource::build).toList()
                , resourceRelationEntities.stream().map(ResourceRelation::build).toList());
    }

    @Override
    public List<Resource> getMarkMenu(Long userId) {
        List<MarkMenu> markMenus = markMenuRepository.getByUserId(userId);
        return markMenus.stream().map(MarkMenu::getResource).toList();
    }
}

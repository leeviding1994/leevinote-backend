package com.leeviding.leevinote.resource.domain.model;

import com.leeviding.leevinote.resource.infrastructure.entity.ResourceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
@Builder
public class Resource {
    private Long id;
    private String code;
    private MenuType menuType;
    private String url;
    private Resource parent;
    private List<Resource> children;

    public static Resource build(ResourceEntity entity){
        return Resource.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .menuType(entity.getMenuType())
                .url(entity.getUrl())
                .build();
    }

    public static List<Resource> buildTree(List<Resource> allResources, List<ResourceRelation> relations){
        // 1. 使用 Map 快速定位节点
        Map<Long, Resource> nodeMap = new HashMap<>();
        for (Resource res : allResources) {
            nodeMap.put(res.getId(), res);
        }

        List<Resource> roots = new ArrayList<>();
        Set<Long> childIds = new HashSet<>();

        // 2. 根据 depth=1 的关系建立层级
        for (ResourceRelation rel : relations) {
            Resource parent = nodeMap.get(rel.getAncestorId());
            Resource child = nodeMap.get(rel.getDescendantId());

            if (parent != null && child != null) {
                // 避免自己指向自己的特殊情况（闭包表中 depth=0 的数据）
                if (!rel.getAncestorId().equals(rel.getDescendantId())) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(child);
                    childIds.add(child.getId());
                }
            }
        }

        // 3. 找出根节点（没有父节点的节点）
        for (Resource node : nodeMap.values()) {
            if (!childIds.contains(node.getId())) {
                roots.add(node);
            }
        }
        return roots;
    }
}

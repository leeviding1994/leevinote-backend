package com.leeviding.leevinote.resource.domain.model;

import com.leeviding.leevinote.resource.infrastructure.entity.ResourceRelationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResourceRelation {
    private Long ancestorId;
    private Long descendantId;
    private Integer depth;

    public static ResourceRelation build(ResourceRelationEntity entity) {
        return ResourceRelation.builder()
                .ancestorId(entity.getAncestorId())
                .descendantId(entity.getDescendantId())
                .depth(entity.getDepth())
                .build();
    }
}

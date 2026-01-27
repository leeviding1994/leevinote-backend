package com.leeviding.leevinote.resource.infrastructure.entity;

import com.leeviding.leevinote.resource.infrastructure.id.ResourceRelationId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "resource_relation")
@IdClass(ResourceRelationId.class)
public class ResourceRelationEntity {

    @Id
    private Long ancestorId;
    @Id
    private Long descendantId;
    private Integer depth;
}

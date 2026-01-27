package com.leeviding.leevinote.resource.infrastructure.entity;

import com.leeviding.leevinote.resource.domain.model.MenuType;
import com.leeviding.leevinote.resource.domain.model.Resource;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "resource")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceEntity {

    @Id
    private Long id;

    private String code;

    @Enumerated(value = EnumType.STRING)
    private MenuType menuType;

    private String url;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ResourceEntity parent;

    public static ResourceEntity build(Resource resource) {
        return ResourceEntity.builder()
                .id(resource.getId())
                .code(resource.getCode())
                .menuType(resource.getMenuType())
                .url(resource.getUrl())
                .build();
    }
}


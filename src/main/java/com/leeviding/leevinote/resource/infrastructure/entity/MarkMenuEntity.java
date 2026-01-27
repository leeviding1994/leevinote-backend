package com.leeviding.leevinote.resource.infrastructure.entity;

import com.leeviding.leevinote.resource.infrastructure.id.MarkMenuId;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "mark_menu")
@IdClass(MarkMenuId.class)
public class MarkMenuEntity {

    @Id
    private Long resourceId;
    @Id
    private Long userId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ResourceEntity resource;

}


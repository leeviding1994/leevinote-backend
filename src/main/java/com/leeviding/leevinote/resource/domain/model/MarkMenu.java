package com.leeviding.leevinote.resource.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MarkMenu {
    private Long resourceId;
    private Long userId;
    private Resource resource;

}

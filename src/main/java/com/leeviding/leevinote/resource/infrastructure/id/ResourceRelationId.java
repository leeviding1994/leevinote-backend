package com.leeviding.leevinote.resource.infrastructure.id;


import java.io.Serial;
import java.io.Serializable;

public class ResourceRelationId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long ancestorId;
    private Long descendantId;
}

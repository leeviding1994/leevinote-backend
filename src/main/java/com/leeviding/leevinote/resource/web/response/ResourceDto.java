package com.leeviding.leevinote.resource.web.response;

import com.leeviding.leevinote.resource.domain.model.MenuType;
import com.leeviding.leevinote.resource.domain.model.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceDto {
    private Long id;
    private String code;
    private MenuType menuType;
    private String url;
    private List<ResourceDto> children;

    public static ResourceDto fromDomain(Resource resource){
        return ResourceDto.builder()
                .id(resource.getId())
                .code(resource.getCode())
                .menuType(resource.getMenuType())
                .url(resource.getUrl())
                .children(parseChildren(resource.getChildren()))
                .build();
    }

    private static List<ResourceDto> parseChildren(List<Resource> children) {
        if (children == null ){
            return null;
        }
        return children.stream().map(ResourceDto::fromDomain).toList();
    }
}

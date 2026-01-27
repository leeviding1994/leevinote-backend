package com.leeviding.leevinote.resource.application;

import com.leeviding.leevinote.resource.domain.model.Resource;
import com.leeviding.leevinote.resource.web.response.ResourceDto;

import java.util.List;

public interface ResourceUseCase {

    /**
     * 获取资源树
     * @param id 资源ID
     * @return 资源树
     */
    List<Resource> getResourceTree(Long id);

    List<Resource> getMarkMenu(Long userId);
}

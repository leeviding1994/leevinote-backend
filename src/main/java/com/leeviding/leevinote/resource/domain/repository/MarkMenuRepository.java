package com.leeviding.leevinote.resource.domain.repository;

import com.leeviding.leevinote.resource.domain.model.MarkMenu;

import java.util.List;

public interface MarkMenuRepository {

    List<MarkMenu> getByUserId(Long userId);
}

package com.leeviding.leevinote.resource.web.controller;

import com.leeviding.leevinote.infrastructure.web.response.Msg;
import com.leeviding.leevinote.resource.application.service.ResourceService;
import com.leeviding.leevinote.resource.domain.model.Resource;
import com.leeviding.leevinote.resource.web.response.ResourceDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@AllArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/{rootId}")
    public ResponseEntity<Msg<List<ResourceDto>>> tree(@PathVariable Long rootId) {
        List<Resource> tree = resourceService.getResourceTree(rootId);
        return ResponseEntity.ok(Msg.success(tree.stream().map(ResourceDto::fromDomain).toList()));
    }

    @GetMapping("/mark/{userId}")
    public ResponseEntity<Msg<List<ResourceDto>>> markMenus(@PathVariable Long userId) {
        List<Resource> markMenus = resourceService.getMarkMenu(userId);
        return ResponseEntity.ok(Msg.success(markMenus.stream().map(ResourceDto::fromDomain).toList()));
    }
}

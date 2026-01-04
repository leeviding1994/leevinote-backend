package com.leeviding.leevinote.note.web.response;

import com.leeviding.leevinote.note.domain.model.NoteVersion;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class NoteVersionDto {
    private Long id;
    private Long noteId;
    private String title;
    private String content;
    private Integer versionNumber;
    private String changeDescription;
    private LocalDateTime createTime;

    public static NoteVersionDto fromDomain(NoteVersion version) {
        return NoteVersionDto.builder()
                .id(version.getId())
                .noteId(version.getNoteId().getValue())
                .title(version.getTitle())
                .content(version.getContent())
                .versionNumber(version.getVersionNumber())
                .changeDescription(version.getChangeDescription())
                .createTime(version.getCreateTime())
                .build();
    }
}

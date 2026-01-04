package com.leeviding.leevinote.note.web.response;

import com.leeviding.leevinote.note.domain.model.Note;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class NoteDto {
    private Long id;
    private String title;
    private String content;
    private Boolean isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static NoteDto fromDomain(Note note) {
        return NoteDto.builder()
                .id(note.getId().getValue())
                .title(note.getTitle())
                .content(note.getContent())
                .isDeleted(note.getIsDeleted())
                .createTime(note.getCreateTime())
                .updateTime(note.getUpdateTime())
                .build();
    }
}

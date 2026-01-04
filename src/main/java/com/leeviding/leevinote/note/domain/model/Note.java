package com.leeviding.leevinote.note.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    private NoteId id;
    private String title;
    private String content;
    private Boolean isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;

    public Note(String title, String content) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("标题不能为空");
        }
        this.id = NoteId.generate();
        this.title = title;
        this.content = content;
        this.isDeleted = false;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    public static Note createOldNote(NoteId id, String title, String content, Boolean isDeleted, LocalDateTime createTime, LocalDateTime updateTime) {
        return Note.builder()
                .id(id)
                .title(title)
                .content(content)
                .isDeleted(isDeleted)
                .createTime(createTime)
                .updateTime(updateTime)
                .build();
    }

    public void updateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("标题不能为空");
        }
        this.title = title;
        this.updateTime = LocalDateTime.now();
    }

    public void updateContent(String content) {
        this.content = content;
        this.updateTime = LocalDateTime.now();
    }

    public void delete() {
        this.isDeleted = true;
        this.deleteTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
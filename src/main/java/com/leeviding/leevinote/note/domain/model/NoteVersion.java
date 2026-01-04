package com.leeviding.leevinote.note.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteVersion {
    private Long id;
    private NoteId noteId;
    private String title;
    private String content;
    private Integer versionNumber;
    private String changeDescription;
    private LocalDateTime createTime;

    public static NoteVersion createInitialVersion(Note note, Integer versionNumber, String changeDescription) {
        return NoteVersion.builder()
                .noteId(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .versionNumber(versionNumber)
                .changeDescription(changeDescription)
                .createTime(LocalDateTime.now())
                .build();
    }

}

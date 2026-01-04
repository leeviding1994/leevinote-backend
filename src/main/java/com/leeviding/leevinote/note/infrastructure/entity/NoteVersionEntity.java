package com.leeviding.leevinote.note.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "note_version")
@AllArgsConstructor
@NoArgsConstructor
public class NoteVersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note_id", nullable = false)
    private Long noteId;

    private String title;
    private String content;
    private Integer versionNumber;
    private String changeDescription;
    private LocalDateTime createTime;
}

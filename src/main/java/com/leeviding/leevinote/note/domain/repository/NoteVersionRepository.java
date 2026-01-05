package com.leeviding.leevinote.note.domain.repository;

import com.leeviding.leevinote.note.domain.model.NoteId;
import com.leeviding.leevinote.note.domain.model.NoteVersion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NoteVersionRepository {
    NoteVersion save(NoteVersion version);
    Optional<NoteVersion> findById(Long id);
    List<NoteVersion> findByNoteId(NoteId noteId);
    Page<NoteVersion> findByNoteId(NoteId noteId, Pageable pageable);
    Optional<NoteVersion> findByNoteIdAndVersionNumber(NoteId noteId, Integer versionNumber);
    Integer getMaxVersionNumberByNoteId(NoteId noteId);
    Optional<NoteVersion> findLatestVersionByNoteId(NoteId noteId);
    // 添加原子性创建版本的方法
    NoteVersion createVersionWithAtomicNumber(NoteId noteId, String title, String content,
                                              String changeDescription, LocalDateTime createTime);
}

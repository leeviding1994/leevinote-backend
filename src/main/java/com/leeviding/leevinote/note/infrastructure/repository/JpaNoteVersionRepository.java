package com.leeviding.leevinote.note.infrastructure.repository;

import com.leeviding.leevinote.note.infrastructure.entity.NoteVersionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaNoteVersionRepository extends JpaRepository<NoteVersionEntity, Long> {
    List<NoteVersionEntity> findByNoteIdOrderByVersionNumberDesc(Long noteId);

    Page<NoteVersionEntity> findByNoteIdOrderByVersionNumberDesc(Long noteId, Pageable pageable);

    Optional<NoteVersionEntity> findByNoteIdAndVersionNumber(Long noteId, Integer versionNumber);

    @Query("SELECT MAX(nv.versionNumber) FROM NoteVersionEntity nv WHERE nv.noteId = :noteId")
    Integer findMaxVersionNumberByNoteId(Long noteId);

    @Modifying
    @Query(value = """
                INSERT INTO note_version (note_id, title, content, version_number, change_description, create_time)
                    VALUES (
                    :noteId, :title, :content,
                    (
                    SELECT COALESCE(MAX(version_number), 0) + 1 FROM note_version WHERE note_id = :noteId
                    ),
                    :changeDescription, :createTime)
                RETURNING *
            """, nativeQuery = true)
    NoteVersionEntity createVersionWithAtomicNumber(
            @Param("noteId") Long noteId,
            @Param("title") String title,
            @Param("content") String content,
            @Param("changeDescription") String changeDescription,
            @Param("createTime") LocalDateTime createTime);
}

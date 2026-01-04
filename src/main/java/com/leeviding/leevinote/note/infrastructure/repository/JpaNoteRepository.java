package com.leeviding.leevinote.note.infrastructure.repository;

import com.leeviding.leevinote.note.infrastructure.entity.NoteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaNoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findByTitleContaining(String title);
    Page<NoteEntity> findByTitleContaining(String title, Pageable pageable);
}
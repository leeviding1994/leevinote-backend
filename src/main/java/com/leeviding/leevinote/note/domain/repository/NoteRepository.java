package com.leeviding.leevinote.note.domain.repository;

import com.leeviding.leevinote.note.domain.model.Note;
import com.leeviding.leevinote.note.domain.model.NoteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface NoteRepository {
    Note save(Note note);
    Optional<Note> findById(NoteId id);
    List<Note> findAll();
    Page<Note> findAll(Pageable pageable);
    List<Note> findByTitleContaining(String title);
    Page<Note> findByTitleContaining(String title, Pageable pageable);
    boolean deleteById(NoteId id);
}

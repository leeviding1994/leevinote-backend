package com.leeviding.leevinote.note.application;

import com.leeviding.leevinote.note.domain.model.Note;
import com.leeviding.leevinote.note.domain.model.NoteVersion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface NoteUseCase {
    Note createNote(String title, String content);
    Note getNoteById(Long id);
    List<Note> getAllNotes();
    Page<Note> getNotesByPage(Pageable pageable);
    List<Note> searchNotesByTitle(String title);
    Page<Note> searchNotesByTitle(String title, Pageable pageable);
    Note updateNote(Long id, String title, String content);
    Note updateNoteTitle(Long id, String title);
    Note updateNoteContent(Long id, String content);
    boolean deleteNote(Long id);

    // 版本控制相关方法
    List<NoteVersion> getNoteVersions(Long noteId);
    Page<NoteVersion> getNoteVersions(Long noteId, Pageable pageable);
    NoteVersion getNoteVersion(Long noteId, Integer versionNumber);
    Note rollbackToVersion(Long noteId, Integer versionNumber);
}
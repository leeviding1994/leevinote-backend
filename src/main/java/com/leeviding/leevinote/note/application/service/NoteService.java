package com.leeviding.leevinote.note.application.service;

import com.leeviding.leevinote.note.domain.model.Note;
import com.leeviding.leevinote.note.domain.model.NoteId;
import com.leeviding.leevinote.note.domain.model.NoteVersion;
import com.leeviding.leevinote.note.domain.repository.NoteRepository;
import com.leeviding.leevinote.note.domain.repository.NoteVersionRepository;
import com.leeviding.leevinote.note.application.NoteUseCase;
import com.leeviding.leevinote.note.domain.service.NoteVersionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class NoteService implements NoteUseCase {
    private final NoteRepository noteRepository;
    private final NoteVersionRepository noteVersionRepository;
    private final NoteVersionService noteVersionService;

    @Override
    @Transactional
    public Note createNote(String title, String content) {
        Note note = new Note(title, content);
        Note savedNote = noteRepository.save(note);
        noteVersionService.createInitialVersion(savedNote);
        return savedNote;
    }

    @Override
    public Note getNoteById(Long id) {
        NoteId noteId = NoteId.of(id);
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new NoSuchElementException("笔记不存在"));
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Page<Note> getNotesByPage(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public List<Note> searchNotesByTitle(String title) {
        return noteRepository.findByTitleContaining(title);
    }

    @Override
    public Page<Note> searchNotesByTitle(String title, Pageable pageable) {
        return noteRepository.findByTitleContaining(title, pageable);
    }

    @Override
    @Transactional
    public Note updateNote(Long id, String title, String content) {
        Note currentNote = getNoteById(id);
        Note oldNote = Note.createOldNote(
                currentNote.getId(),
                currentNote.getTitle(),
                currentNote.getContent(),
                currentNote.getIsDeleted(),
                currentNote.getCreateTime(),
                currentNote.getUpdateTime()
        );

        currentNote.updateTitle(title);
        currentNote.updateContent(content);

        Note updatedNote = noteRepository.save(currentNote);

        noteVersionService.createUpdateVersion(oldNote, updatedNote);
        return updatedNote;
    }

    @Override
    @Transactional
    public Note updateNoteTitle(Long id, String title) {
        Note oldNote = getNoteById(id);
        Note newNote = new Note(oldNote.getTitle(), oldNote.getContent());
        newNote.setId(oldNote.getId());
        newNote.updateTitle(title);

        Note savedNote = noteRepository.save(newNote);
        noteVersionService.createUpdateVersion(oldNote, savedNote);
        return savedNote;
    }

    @Override
    @Transactional
    public Note updateNoteContent(Long id, String content) {
        Note oldNote = getNoteById(id);
        Note newNote = new Note(oldNote.getTitle(), oldNote.getContent());
        newNote.setId(oldNote.getId());
        newNote.updateContent(content);

        Note savedNote = noteRepository.save(newNote);
        noteVersionService.createUpdateVersion(oldNote, savedNote);
        return savedNote;
    }

    @Override
    public boolean deleteNote(Long id) {
        NoteId noteId = NoteId.of(id);
        return noteRepository.deleteById(noteId);
    }

    @Override
    public List<NoteVersion> getNoteVersions(Long noteId) {
        NoteId id = NoteId.of(noteId);
        return noteVersionRepository.findByNoteId(id);
    }

    @Override
    public Page<NoteVersion> getNoteVersions(Long noteId, Pageable pageable) {
        NoteId id = NoteId.of(noteId);
        return noteVersionRepository.findByNoteId(id, pageable);
    }

    @Override
    public NoteVersion getNoteVersion(Long noteId, Integer versionNumber) {
        NoteId id = NoteId.of(noteId);
        return noteVersionRepository.findByNoteIdAndVersionNumber(id, versionNumber)
                .orElseThrow(() -> new NoSuchElementException("版本不存在"));
    }

    @Override
    public Note rollbackToVersion(Long noteId, Integer versionNumber) {
        NoteVersion version = getNoteVersion(noteId, versionNumber);
        Note currentNote = getNoteById(noteId);

        // 恢复到指定版本
        currentNote.setTitle(version.getTitle());
        currentNote.setContent(version.getContent());

        // 保存恢复后的版本，同时创建新的版本记录
        return noteRepository.save(currentNote);
    }
}
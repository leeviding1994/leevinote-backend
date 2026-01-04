package com.leeviding.leevinote.note.web.controller;

import com.leeviding.leevinote.note.application.NoteUseCase;
import com.leeviding.leevinote.note.domain.model.Note;
import com.leeviding.leevinote.note.domain.model.NoteVersion;
import com.leeviding.leevinote.note.web.request.NoteCmd;
import com.leeviding.leevinote.note.web.request.NoteQueryObj;
import com.leeviding.leevinote.note.web.response.NoteDto;
import com.leeviding.leevinote.note.web.response.NoteVersionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteUseCase noteUseCase;

    public NoteController(NoteUseCase noteUseCase) {
        this.noteUseCase = noteUseCase;
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteCmd cmd) {
        Note note = noteUseCase.createNote(cmd.getTitle(), cmd.getContent());
        return ResponseEntity.ok(NoteDto.fromDomain(note));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Long id) {
        Note note = noteUseCase.getNoteById(id);
        return ResponseEntity.ok(NoteDto.fromDomain(note));
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        List<Note> notes = noteUseCase.getAllNotes();
        List<NoteDto> dtos = notes.stream()
                .map(NoteDto::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<NoteDto>> getNotesByPage(@PageableDefault Pageable pageable) {
        Page<Note> notes = noteUseCase.getNotesByPage(pageable);
        Page<NoteDto> dtos = notes.map(NoteDto::fromDomain);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<NoteDto>> searchNotesByTitle(@RequestParam String title) {
        List<Note> notes = noteUseCase.searchNotesByTitle(title);
        List<NoteDto> dtos = notes.stream()
                .map(NoteDto::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search/page")
    public ResponseEntity<Page<NoteDto>> searchNotesByTitle(@RequestParam String title, @PageableDefault Pageable pageable) {
        Page<Note> notes = noteUseCase.searchNotesByTitle(title, pageable);
        Page<NoteDto> dtos = notes.map(NoteDto::fromDomain);
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Long id, @RequestBody NoteCmd cmd) {
        Note note = noteUseCase.updateNote(id, cmd.getTitle(), cmd.getContent());
        return ResponseEntity.ok(NoteDto.fromDomain(note));
    }

    @PutMapping("/{id}/title")
    public ResponseEntity<NoteDto> updateNoteTitle(@PathVariable Long id, @RequestBody NoteCmd cmd) {
        Note note = noteUseCase.updateNoteTitle(id, cmd.getTitle());
        return ResponseEntity.ok(NoteDto.fromDomain(note));
    }

    @PutMapping("/{id}/content")
    public ResponseEntity<NoteDto> updateNoteContent(@PathVariable Long id, @RequestBody NoteCmd cmd) {
        Note note = noteUseCase.updateNoteContent(id, cmd.getContent());
        return ResponseEntity.ok(NoteDto.fromDomain(note));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteNote(@PathVariable Long id) {
        boolean deleted = noteUseCase.deleteNote(id);
        return ResponseEntity.ok(deleted);
    }

    // 版本控制相关接口
    @GetMapping("/{noteId}/versions")
    public ResponseEntity<List<NoteVersionDto>> getNoteVersions(@PathVariable Long noteId) {
        List<NoteVersion> versions = noteUseCase.getNoteVersions(noteId);
        List<NoteVersionDto> dtos = versions.stream()
                .map(NoteVersionDto::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{noteId}/versions/page")
    public ResponseEntity<Page<NoteVersionDto>> getNoteVersions(@PathVariable Long noteId, @PageableDefault Pageable pageable) {
        Page<NoteVersion> versions = noteUseCase.getNoteVersions(noteId, pageable);
        Page<NoteVersionDto> dtos = versions.map(NoteVersionDto::fromDomain);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{noteId}/versions/{versionNumber}")
    public ResponseEntity<NoteVersionDto> getNoteVersion(@PathVariable Long noteId, @PathVariable Integer versionNumber) {
        NoteVersion version = noteUseCase.getNoteVersion(noteId, versionNumber);
        return ResponseEntity.ok(NoteVersionDto.fromDomain(version));
    }

    @PostMapping("/{noteId}/rollback/{versionNumber}")
    public ResponseEntity<NoteDto> rollbackToVersion(@PathVariable Long noteId, @PathVariable Integer versionNumber) {
        Note note = noteUseCase.rollbackToVersion(noteId, versionNumber);
        return ResponseEntity.ok(NoteDto.fromDomain(note));
    }
}
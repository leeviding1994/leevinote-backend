package com.leeviding.leevinote.note.infrastructure.repository;

import com.leeviding.leevinote.note.domain.model.Note;
import com.leeviding.leevinote.note.domain.model.NoteId;
import com.leeviding.leevinote.note.domain.model.NoteVersion;
import com.leeviding.leevinote.note.domain.repository.NoteRepository;
import com.leeviding.leevinote.note.domain.repository.NoteVersionRepository;
import com.leeviding.leevinote.note.infrastructure.entity.NoteEntity;
import com.leeviding.leevinote.note.infrastructure.mapper.NoteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class NoteRepositoryImpl implements NoteRepository {
    private final JpaNoteRepository jpaNoteRepository;
    private final NoteMapper noteMapper;

    public NoteRepositoryImpl(JpaNoteRepository jpaNoteRepository, NoteMapper noteMapper) {
        this.jpaNoteRepository = jpaNoteRepository;
        this.noteMapper = noteMapper;
    }

    @Override
    public Note save(Note note) {
        NoteEntity entity = noteMapper.toEntity(note);
        NoteEntity savedEntity = jpaNoteRepository.save(entity);
        return noteMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Note> findById(NoteId id) {
        return jpaNoteRepository.findById(id.getValue())
                .map(noteMapper::toDomain);
    }

    @Override
    public List<Note> findAll() {
        return jpaNoteRepository.findAll().stream()
                .map(noteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return jpaNoteRepository.findAll(pageable)
                .map(noteMapper::toDomain);
    }

    @Override
    public List<Note> findByTitleContaining(String title) {
        return jpaNoteRepository.findByTitleContaining(title).stream()
                .map(noteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Note> findByTitleContaining(String title, Pageable pageable) {
        return jpaNoteRepository.findByTitleContaining(title, pageable).map(noteMapper::toDomain);
    }

    @Override
    public boolean deleteById(NoteId id) {
        Optional<NoteEntity> optionalEntity = jpaNoteRepository.findById(id.getValue());
        if (optionalEntity.isPresent()) {
            NoteEntity entity = optionalEntity.get();
            entity.setIsDeleted(true);
            entity.setDeleteTime(java.time.LocalDateTime.now());
            entity.setUpdateTime(java.time.LocalDateTime.now());
            jpaNoteRepository.save(entity);
            return true;
        }
        return false;
    }
}

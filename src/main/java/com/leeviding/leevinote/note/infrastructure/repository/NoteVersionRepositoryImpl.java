package com.leeviding.leevinote.note.infrastructure.repository;

import com.leeviding.leevinote.note.domain.model.NoteId;
import com.leeviding.leevinote.note.domain.model.NoteVersion;
import com.leeviding.leevinote.note.domain.repository.NoteVersionRepository;
import com.leeviding.leevinote.note.infrastructure.entity.NoteVersionEntity;
import com.leeviding.leevinote.note.infrastructure.mapper.NoteVersionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class NoteVersionRepositoryImpl implements NoteVersionRepository {
    private final JpaNoteVersionRepository jpaNoteVersionRepository;
    private final NoteVersionMapper noteVersionMapper;

    public NoteVersionRepositoryImpl(JpaNoteVersionRepository jpaNoteVersionRepository, NoteVersionMapper noteVersionMapper) {
        this.jpaNoteVersionRepository = jpaNoteVersionRepository;
        this.noteVersionMapper = noteVersionMapper;
    }

    @Override
    public NoteVersion save(NoteVersion version) {
        NoteVersionEntity entity = noteVersionMapper.toEntity(version);
        NoteVersionEntity savedEntity = jpaNoteVersionRepository.save(entity);
        return noteVersionMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<NoteVersion> findById(Long id) {
        return jpaNoteVersionRepository.findById(id)
                .map(noteVersionMapper::toDomain);
    }

    @Override
    public List<NoteVersion> findByNoteId(NoteId noteId) {
        return jpaNoteVersionRepository.findByNoteIdOrderByVersionNumberDesc(noteId.getValue()).stream()
                .map(noteVersionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Page<NoteVersion> findByNoteId(NoteId noteId, Pageable pageable) {
        return jpaNoteVersionRepository.findByNoteIdOrderByVersionNumberDesc(noteId.getValue(), pageable)
                .map(noteVersionMapper::toDomain);
    }

    @Override
    public Optional<NoteVersion> findByNoteIdAndVersionNumber(NoteId noteId, Integer versionNumber) {
        return jpaNoteVersionRepository.findByNoteIdAndVersionNumber(noteId.getValue(), versionNumber)
                .map(noteVersionMapper::toDomain);
    }

    @Override
    public Integer getMaxVersionNumberByNoteId(NoteId noteId) {
        return jpaNoteVersionRepository.findMaxVersionNumberByNoteId(noteId.getValue());
    }

    @Override
    public Optional<NoteVersion> findLatestVersionByNoteId(NoteId noteId) {
        return jpaNoteVersionRepository.findByNoteIdOrderByVersionNumberDesc(noteId.getValue())
                .stream()
                .findFirst()
                .map(noteVersionMapper::toDomain);
    }

    @Override
    public NoteVersion createVersionWithAtomicNumber(NoteId noteId, String title, String content,
                                                     String changeDescription, LocalDateTime createTime) {
        NoteVersionEntity entity = jpaNoteVersionRepository.createVersionWithAtomicNumber(
                noteId.getValue(), title, content, changeDescription, createTime);
        return noteVersionMapper.toDomain(entity);
    }
}

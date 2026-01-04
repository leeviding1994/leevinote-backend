package com.leeviding.leevinote.note.infrastructure.mapper;

import com.leeviding.leevinote.note.domain.model.NoteId;
import com.leeviding.leevinote.note.domain.model.NoteVersion;
import com.leeviding.leevinote.note.infrastructure.entity.NoteVersionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NoteVersionMapper {
    @Mapping(target = "noteId", source = "noteId", qualifiedByName = "toNoteId")
    NoteVersion toDomain(NoteVersionEntity entity);

    @Mapping(target = "noteId", source = "noteId", qualifiedByName = "fromNoteId")
    NoteVersionEntity toEntity(NoteVersion version);

    @Named("toNoteId")
    default NoteId toNoteId(Long id) {
        return id == null ? null : NoteId.of(id);
    }

    @Named("fromNoteId")
    default Long fromNoteId(NoteId id) {
        return id == null ? null : id.getValue();
    }
}

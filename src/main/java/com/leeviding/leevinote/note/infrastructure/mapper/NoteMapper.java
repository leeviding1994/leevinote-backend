package com.leeviding.leevinote.note.infrastructure.mapper;

import com.leeviding.leevinote.note.domain.model.Note;
import com.leeviding.leevinote.note.domain.model.NoteId;
import com.leeviding.leevinote.note.infrastructure.entity.NoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    @Mapping(target = "id", source = "id", qualifiedByName = "toNoteId")
    Note toDomain(NoteEntity entity);

    @Mapping(target = "id", source = "id", qualifiedByName = "fromNoteId")
    NoteEntity toEntity(Note note);

    @Named("toNoteId")
    default NoteId toNoteId(Long id) {
        return id == null ? null : NoteId.of(id);
    }

    @Named("fromNoteId")
    default Long fromNoteId(NoteId id) {
        return id == null ? null : id.getValue();
    }
}

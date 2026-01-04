package com.leeviding.leevinote.note.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
public class NoteId {
    private final Long value;

    private NoteId(Long value) {
        this.value = value;
    }

    public static NoteId of(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("无效的笔记ID");
        }
        return new NoteId(value);
    }

    // 用于新建笔记时生成ID（如果使用UUID）
    public static NoteId generate() {
        // 实际使用中可根据需要选择ID生成策略
        return new NoteId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    }
}

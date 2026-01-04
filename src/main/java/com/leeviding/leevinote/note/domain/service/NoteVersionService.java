package com.leeviding.leevinote.note.domain.service;

import com.leeviding.leevinote.note.domain.model.Note;
import com.leeviding.leevinote.note.domain.model.NoteVersion;
import com.leeviding.leevinote.note.domain.repository.NoteVersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteVersionService {
    private final NoteVersionRepository noteVersionRepository;

    /**
     * 创建笔记版本
     * @param note 笔记对象
     * @param changeDescription 变更描述
     * @return 创建的版本
     */
    @Transactional
    public NoteVersion createVersion(Note note, String changeDescription) {
        // 获取最新版本
        Optional<NoteVersion> latestVersionOpt = noteVersionRepository.findLatestVersionByNoteId(note.getId());

        // 检查内容是否与最新版本相同
        if (latestVersionOpt.isPresent()) {
            NoteVersion latestVersion = latestVersionOpt.get();
            if (note.getTitle().equals(latestVersion.getTitle()) &&
                    note.getContent().equals(latestVersion.getContent())) {
                // 内容未变化，不创建新版本
                return null;
            }
        }

        // 使用原子操作创建新版本
        Integer maxVersion = noteVersionRepository.getMaxVersionNumberByNoteId(note.getId());
        Integer newVersion = (maxVersion == null) ? 1 : maxVersion + 1;

        NoteVersion version = NoteVersion.createInitialVersion(note, newVersion, changeDescription);
        return noteVersionRepository.save(version);
    }

    /**
     * 创建笔记的初始版本
     * @param note 新创建的笔记
     * @return 创建的版本
     */
    @Transactional
    public NoteVersion createInitialVersion(Note note) {
        return createVersion(note, "创建笔记");
    }

    // 其他方法保持不变，但createInitialVersion和createUpdateVersion需要处理返回null的情况
    @Transactional
    public NoteVersion createUpdateVersion(Note oldNote, Note newNote) {
        // 检查内容是否实际变化
        if (oldNote.getTitle().equals(newNote.getTitle()) &&
                oldNote.getContent().equals(newNote.getContent())) {
            return null;
        }

        String changeDescription = getChangeDescription(oldNote, newNote);
        return createVersion(newNote, changeDescription);
    }

    private String getChangeDescription(Note oldNote, Note newNote) {
        StringBuilder description = new StringBuilder();
        if (!oldNote.getTitle().equals(newNote.getTitle())) {
            description.append("修改标题; ");
        }
        if (!oldNote.getContent().equals(newNote.getContent())) {
            description.append("修改内容; ");
        }
        return description.length() > 0 ? description.substring(0, description.length() - 2) : "无变更";
    }
}
package com.leeviding.leevinote.note.web.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteCmd {
    private String title;
    private String content;
}
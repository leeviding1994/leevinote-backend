package com.leeviding.leevinote.note.web.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteQueryObj {
    private String title;
    private Integer page;
    private Integer size;
}

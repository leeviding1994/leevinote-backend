package com.leeviding.leevinote.note.web.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteQueryObj {
    @Builder.Default
    private String title = "";
}

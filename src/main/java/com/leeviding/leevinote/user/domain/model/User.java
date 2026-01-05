package com.leeviding.leevinote.user.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean isEnabled;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
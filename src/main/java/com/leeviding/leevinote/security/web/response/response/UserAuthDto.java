package com.leeviding.leevinote.security.web.response.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthDto {
    private Long id;
    private String username;
    private String token;

    public static UserAuthDto buildUser(Long id, String username, String token) {
        return UserAuthDto.builder()
                .id(id)
                .username(username)
                .token(token)
                .build();
    }
}

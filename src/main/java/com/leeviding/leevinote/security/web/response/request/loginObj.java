package com.leeviding.leevinote.security.web.response.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class loginObj {
    private String username;
    private String password;
}

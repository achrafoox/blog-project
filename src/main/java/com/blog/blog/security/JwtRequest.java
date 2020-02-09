package com.blog.blog.security;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtRequest {
    private String username;
    private String password;
}

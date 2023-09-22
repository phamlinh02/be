package com.maidoo.maidoo.service.dto.user;

import com.maidoo.maidoo.config.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String role;

    public JwtResponse(String token, Long id, String username, String email, String roles) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.role = roles;
    }
}

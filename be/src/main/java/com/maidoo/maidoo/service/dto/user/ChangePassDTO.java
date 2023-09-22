package com.maidoo.maidoo.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassDTO {
    private Long id;
    private String oldPassword;
    private String password;
}

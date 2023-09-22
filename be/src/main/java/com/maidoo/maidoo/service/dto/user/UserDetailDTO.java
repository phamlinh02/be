package com.maidoo.maidoo.service.dto.user;

import com.maidoo.maidoo.config.Constant;
import com.maidoo.maidoo.domain.SharedAuditingEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDTO extends SharedAuditingEntity {
    private Long id;
    private String fullname;
    private String email;
    private String phone;

    private String username;
    private String password;
    private Constant.ROLE_USER role;

}
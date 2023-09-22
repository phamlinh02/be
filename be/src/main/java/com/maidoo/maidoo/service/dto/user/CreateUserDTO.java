package com.maidoo.maidoo.service.dto.user;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.maidoo.maidoo.config.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    private String fullname;
    @Email(message = "Email phải đúng đúng dạng")
    private String email;
    @Pattern(regexp = "(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\\b", message = "Số điện thoại không đúng định đạng")
    private String phone;
    @Size(min = 6, max = 25, message = "Tài khoản phải từ 6 đến 25 ký tự")
    private String username;
    @Size(min = 6, max = 100, message = "Mật khẩu phải từ 6 đến 100 ký tự")
    private String password;
    @Enumerated(EnumType.STRING)
    private Constant.ROLE_USER role;
    
}

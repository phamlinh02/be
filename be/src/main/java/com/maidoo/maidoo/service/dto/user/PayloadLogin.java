package com.maidoo.maidoo.service.dto.user;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadLogin {
    @Size(min = 6, max = 25, message = "Tài khoản phải từ 6 đến 25 ký tự")
    private String username;
    @Size(min = 6, max = 100, message = "Mật khẩu phải từ 6 đến 100 ký tự")
    private String password;
}

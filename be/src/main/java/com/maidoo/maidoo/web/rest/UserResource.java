package com.maidoo.maidoo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maidoo.maidoo.service.UserService;
import com.maidoo.maidoo.service.dto.ResponseDTO;
import com.maidoo.maidoo.service.dto.user.ChangePassDTO;
import com.maidoo.maidoo.service.dto.user.CreateUserDTO;
import com.maidoo.maidoo.service.dto.user.PayloadLogin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RequestMapping("/api/maidoo/user")
@AllArgsConstructor
@RestController 
public class UserResource {
    private final UserService userService;

    @GetMapping("/get-all")
    @ApiOperation(value = "Lấy danh sách người dùng")
    public ResponseDTO getAllUser(Pageable pageable) {
        return ResponseDTO.success(this.userService.getAllUser(pageable));
    }

    @PostMapping("/save") 
    @ApiOperation(value = "Thêm người dùng")
    public ResponseDTO saveUser(@Validated @RequestBody CreateUserDTO user) {
        return ResponseDTO.success(this.userService.saveUser(user));
    }

    @PostMapping("/register") 
    @ApiOperation(value = "Thêm người dùng")
    public ResponseDTO register(@Validated @RequestBody CreateUserDTO user) {
        return ResponseDTO.success(this.userService.register(user));
    }

    @GetMapping("/get-user")
    @ApiOperation(value = "Lấy thông tin người dùng")
    public ResponseDTO loadUserByUsername(String username) {
        return ResponseDTO.success(this.userService.loadUserByUsername(username));
    }

    @PostMapping("/login")
    @ApiOperation(value = "Đăng nhập")
    public ResponseDTO login(@Validated @RequestBody PayloadLogin user) {
        return ResponseDTO.success(this.userService.login(user));
    }
    @PostMapping("/update-pass")
    @ApiOperation(value = "Đổi mật khẩu")
    public ResponseDTO changePassword(@Validated @RequestBody ChangePassDTO changePassDTO) {
        return ResponseDTO.success(this.userService.changePassword(changePassDTO));
    }
    @GetMapping("/test-send-email")
    @ApiOperation(value = "Test gửi email")
    public ResponseDTO existsByUsername() {
        return ResponseDTO.success(this.userService.checkAsyncSendEmail());
    }
}

package com.maidoo.maidoo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maidoo.maidoo.config.Constant.ROLE_USER;
import com.maidoo.maidoo.config.exception.common.NotFoundException;
import com.maidoo.maidoo.domain.User;
import com.maidoo.maidoo.repository.IUserRepository;
import com.maidoo.maidoo.security.jwt.JwtUtils;
import com.maidoo.maidoo.security.service.UserDetailsImpl;
import com.maidoo.maidoo.service.dto.user.ChangePassDTO;
import com.maidoo.maidoo.service.dto.user.CreateUserDTO;
import com.maidoo.maidoo.service.dto.user.JwtResponse;
import com.maidoo.maidoo.service.dto.user.PayloadLogin;
import com.maidoo.maidoo.service.dto.user.UserDTO;
import com.maidoo.maidoo.service.dto.user.UserDetailDTO;
import com.maidoo.maidoo.service.mapper.MapperUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final IUserRepository userRepository;
    private final NotificationService notificationService;

    @Value("${spring.mail.username}")
    private String usernameEmail;
    @Value("${spring.mail.password}")
    private String passwordEmail;

    public Page<UserDTO> getAllUser(Pageable pageable) {
        Page<UserDTO> users = MapperUtils.mapEntityPageIntoDtoPage(this.userRepository.findAll(pageable), UserDTO.class);
        return users;
    }

    public UserDTO saveUser(CreateUserDTO user) {
        if (user.getRole() == null) {
            throw new NotFoundException("Không được để trống role");
        }
        if (this.userRepository.existsByUsername(user.getUsername())) {
            throw new NotFoundException("Tên đăng nhập đã tồn tại");
        } else {
            User userEntity = MapperUtils.map(user, User.class);
            userEntity.setPassword(encoder.encode(user.getPassword()));
            return MapperUtils.map(this.userRepository.save(userEntity), UserDTO.class);
        }
    }

    public UserDetailDTO loadUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Không tìm thấy user"));
        
        return MapperUtils.map(user, UserDetailDTO.class);
    }

    public JwtResponse login(PayloadLogin payloadLogin) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                payloadLogin.getUsername(), payloadLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        
        return new JwtResponse(jwt, "Bearer",
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles.get(0)
        );
    }

    public UserDTO register(CreateUserDTO user) {
        if (this.userRepository.existsByUsername(user.getUsername())) {
            throw new NotFoundException("Tên đăng nhập đã tồn tại");
        } else {
            User userEntity = MapperUtils.map(user, User.class);
            userEntity.setPassword(encoder.encode(user.getPassword()));
            userEntity.setRole(ROLE_USER.GUEST);
            return MapperUtils.map(this.userRepository.save(userEntity), UserDTO.class);
        }
    }
    // Change password
    public UserDTO changePassword(ChangePassDTO changePassDTO) {
        User user = this.userRepository.findById(changePassDTO.getId()).orElseThrow(() -> new NotFoundException("Không tìm thấy user"));
        if (!encoder.matches(changePassDTO.getOldPassword(), user.getPassword())) {
            throw new NotFoundException("Mật khẩu cũ không đúng");
        } 
        user.setPassword(encoder.encode(changePassDTO.getPassword()));
        return MapperUtils.map(this.userRepository.save(user), UserDTO.class);
    }

    public String checkAsyncSendEmail() {
        notificationService.sendMail(List.of("quocdbph17660@fpt.edu.vn"), "sub", "Hello World");
        return "email";
    }
}   

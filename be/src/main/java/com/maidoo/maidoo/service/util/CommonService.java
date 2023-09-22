package com.maidoo.maidoo.service.util;

import com.maidoo.maidoo.config.exception.common.NotFoundException;
import com.maidoo.maidoo.domain.User;
import com.maidoo.maidoo.repository.IUserRepository;
import com.maidoo.maidoo.security.service.UserDetailsImpl;

// import com.maidoo.maidoo.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CommonService {

    private final IUserRepository userRepository;

    public CommonService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // public User getCurrentUser() {
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // String currentUser = authentication.getName();
    // if (!DataUtils.isNullObject(currentUser)) {
    // return userRepository.findById(5L).orElseThrow(NotFoundException::new);
    // }
    // return
    // userRepository.findByLogin(currentUser).orElseThrow(NotFoundException::new);
    // }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return this.userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("Không tìm thấy user"));
    }
}

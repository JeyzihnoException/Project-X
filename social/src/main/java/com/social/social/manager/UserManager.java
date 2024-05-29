package com.social.social.manager;

import com.social.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository repository;

}

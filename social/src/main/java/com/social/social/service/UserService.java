package com.social.social.service;

import com.social.social.manager.UserManager;
import com.social.social.model.dto.FriendDTO;
import com.social.social.model.dto.SelfDataDTO;
import com.social.social.model.dto.UserDTO;
import com.social.social.model.entity.User;
import com.social.social.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserManager userManager;

    public UserDTO getUserDetails(UUID userUuid) {
        User user = userManager.getUserByUuid(userUuid).orElseThrow();
        return UserDTO
                .builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .friends(user.getFriends()
                        .stream()
                        .map(friend -> FriendDTO
                                .builder()
                                .firstName(friend.getFirstName())
                                .uuid(friend.getUuid())
                                .build())
                        .collect(Collectors.toSet()))
                .selfData(SelfDataDTO
                        .builder()
                        .city("Moscow")
                        .country("Russia")
                        .dateOfBirth(Util.dateInFormat(user.getDateOfBirth()))
                        .build())
                .build();
    }
}
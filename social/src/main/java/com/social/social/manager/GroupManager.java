package com.social.social.manager;

import com.social.social.repository.DialogueRepository;
import com.social.social.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupManager {
    private final GroupRepository repository;

}

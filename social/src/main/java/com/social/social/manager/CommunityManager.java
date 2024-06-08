package com.social.social.manager;

import com.social.social.model.entity.Community;
import com.social.social.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommunityManager {
    private final CommunityRepository repository;

    public void save(Community community) {
        repository.save(community);
    }

    public Optional<Community> getByName(String name) {
        return repository.findCommunityByName(name);
    }

    public List<Community> getAll(UUID uuid) {
        return repository.findAllByMembersUuid(uuid);
    }
}

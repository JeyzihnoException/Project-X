package com.social.social.repository;

import com.social.social.model.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommunityRepository extends JpaRepository<Community, UUID> {

    Optional<Community> findCommunityByName(String name);

    List<Community> findAllByMembersUuid(UUID uuid);
}

package com.social.social.service;

import com.social.social.manager.CommunityManager;
import com.social.social.manager.UserManager;
import com.social.social.model.dto.CommunityDTO;
import com.social.social.model.entity.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityManager communityManager;
    private final UserManager userManager;

    public void create(CommunityDTO communityDTO) {
        if (communityManager.getByName(communityDTO.getName()).isEmpty()) {
            Community community = new Community(communityDTO.getName(),
                    userManager.getUserByUuid(communityDTO.getAuthorId())
                            .orElseThrow());
            communityManager.save(community);
        } else {
            throw new RuntimeException("Community with name already exist!");
        }
    }

    public List<CommunityDTO> getAll(UUID userUuid) {
        return communityManager
                .getAll(userUuid)
                .stream()
                .map(community -> CommunityDTO
                        .builder()
                        .name(community.getName())
                        .authorId(community.getAuthor().getUuid())
                        .build())
                .toList();
    }

}

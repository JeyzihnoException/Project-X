package com.social.social.repository;

import com.social.social.model.entity.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DialogueRepository extends JpaRepository<Dialogue, UUID> {
}

package com.messenger.messenger.repository;

import com.messenger.messenger.model.entity.Dialogue;
import com.messenger.messenger.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DialogueRepository extends JpaRepository<Dialogue, UUID> {

    @Query("SELECT d FROM Dialogue d JOIN d.owners u1 JOIN d.owners u2 WHERE u1.uuid = :uuid1 AND u2.uuid = :uuid2")
    Optional<Dialogue> findDialogueByOwners(@Param("uuid1") UUID uuid1, @Param("uuid2") UUID uuid2);

    List<Dialogue> findDialoguesByOwnersUuidIn(List<UUID> uuids);

}

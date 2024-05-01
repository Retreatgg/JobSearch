package com.example.demo.repository;

import com.example.demo.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("select c from Chat c where c.toUserEmail like :toUser and c.fromUserEmail like :fromUser")
    List<Chat> findByToUserAndFromUser(String toUser, String fromUser);
}

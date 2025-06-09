package com.koyoi.main.repository;

import com.koyoi.main.entity.ChatSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatSummaryRepository extends JpaRepository<ChatSummary, Long> {
    List<ChatSummary> findByUserId(String userId);
}

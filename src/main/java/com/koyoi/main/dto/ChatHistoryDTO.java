package com.koyoi.main.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ChatHistoryDTO {
    private List<Map<String, Object>> messages;
}


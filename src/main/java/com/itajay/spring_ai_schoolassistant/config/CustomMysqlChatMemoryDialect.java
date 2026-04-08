package com.itajay.spring_ai_schoolassistant.config;

import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepositoryDialect;

public class CustomMysqlChatMemoryDialect implements JdbcChatMemoryRepositoryDialect {

    @Override
    public String getSelectMessagesSql() {
        return "SELECT content, type FROM chat_memory WHERE conversation_id = ? ORDER BY `timestamp`";
    }
    @Override
    public String getInsertMessageSql() {
        return "INSERT INTO chat_memory (conversation_id, content, type, `timestamp`) VALUES (?, ?, ?, ?)";
    }
    @Override
    public String getSelectConversationIdsSql() {
        return "SELECT DISTINCT conversation_id FROM chat_memory";
    }
    @Override
    public String getDeleteMessagesSql() {
        return "DELETE FROM chat_memory WHERE conversation_id = ?";
    }
}
package com.itajay.spring_ai_schoolassistant.config;

import com.itajay.spring_ai_schoolassistant.Tools.timeTool.TimeTool;
import com.itajay.spring_ai_schoolassistant.consistant.SystemConsistant;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ChatClientAutoConfiguration {

    @Bean
    public JdbcChatMemoryRepository jdbcChatMemoryRepository(JdbcTemplate jdbcTemplate) {
        return JdbcChatMemoryRepository.builder()
                .jdbcTemplate(jdbcTemplate)
                .dialect(new CustomMysqlChatMemoryDialect())
                .build();
    }
    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .build();

    }

    @Bean
    public ChatClient mainClient(ChatModel chatModel, ChatMemory chatMemory) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SystemConsistant.MAIN_AGENT_SYSTEMP_PROMPT)
                .defaultAdvisors(SimpleLoggerAdvisor.builder().build(),
                MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(new TimeTool())
            .build();
    }
    @Bean
    public ChatClient infoClient(ChatModel chatModel, ChatMemory chatMemory) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SystemConsistant.INFO_AGENT_SYSTEMP_PROMPT)
                .defaultAdvisors(SimpleLoggerAdvisor.builder().build(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(new TimeTool())
                .build();
    }
    @Bean
    public ChatClient scheduleClient(ChatModel chatModel, ChatMemory chatMemory) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SystemConsistant.SCHEDULE_AGENT_SYSTEMP_PROMPT)
                .defaultAdvisors(SimpleLoggerAdvisor.builder().build(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(new TimeTool())
                .build();
    }
    @Bean
    public ChatClient knowledgeClient(ChatModel chatModel, ChatMemory chatMemory) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SystemConsistant.KNOWLEDGE_AGENT_SYSTEMP_PROMPT)
                .defaultAdvisors(SimpleLoggerAdvisor.builder().build(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                       )
                .defaultTools(new TimeTool())
                .build();
    }
}

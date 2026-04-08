package com.itajay.spring_ai_schoolassistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.ai.model.openai.autoconfigure.OpenAiAudioSpeechAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude =OpenAiAudioSpeechAutoConfiguration.class)
@MapperScan("com.itajay.spring_ai_schoolassistant.mapper")
public class SpringAiSchoolAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiSchoolAssistantApplication.class, args);
    }

}

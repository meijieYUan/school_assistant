package com.itajay.spring_ai_schoolassistant.agent;

import com.itajay.spring_ai_schoolassistant.Tools.wetherTool.WeatherTool;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service

public class InfoAgent {
    @Autowired
    @Qualifier("infoClient")
    private ChatClient infoClient;
    @Autowired
    private  WeatherTool weatherTool;
    public String handle(String userMessage){
        return infoClient.prompt()
                .user(userMessage)
                .tools(weatherTool)
                .call()
                .content();
    }
}
